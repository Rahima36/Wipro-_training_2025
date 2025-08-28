import { Injectable, computed, signal } from '@angular/core';

// A type definition for the Food item.
export interface Food {
  id: number;
  name: string;
  price: number;
  image: string;
}

// A type definition for a cart item.
export interface CartItem extends Food {
  quantity: number;
}

@Injectable({
  providedIn: 'root',
})
export class FoodService {
  // Use a signal for the login state.
  isLoggedIn = signal<boolean>(false);

  // A signal to toggle the cart view.
  viewCart = signal<boolean>(false);

  // A signal for the shopping cart, initialized as an empty array.
  cart = signal<CartItem[]>([]);

  // A computed signal to get the total number of items in the cart.
  cartCount = computed(() => this.cart().reduce((total, item) => total + item.quantity, 0));

  // Dummy data for the food items.
  foods: Food[] = [
    { id: 1, name: 'Burger', price: 9.99, image: 'https://placehold.co/100x100/F06318/fff?text=Burger' },
    { id: 2, name: 'Pizza', price: 12.50, image: 'https://placehold.co/100x100/F06318/fff?text=Pizza' },
    { id: 3, name: 'Pasta', price: 11.25, image: 'https://placehold.co/100x100/F06318/fff?text=Pasta' },
    { id: 4, name: 'Salad', price: 7.00, image: 'https://placehold.co/100x100/F06318/fff?text=Salad' },
    { id: 5, name: 'Sushi', price: 15.75, image: 'https://placehold.co/100x100/F06318/fff?text=Sushi' },
    { id: 6, name: 'Tacos', price: 8.50, image: 'https://placehold.co/100x100/F06318/fff?text=Tacos' },
  ];

  constructor() {}

  login() {
    this.isLoggedIn.set(true);
  }

  logout() {
    this.isLoggedIn.set(false);
    this.viewCart.set(false);
    this.cart.set([]);
  }

  addToCart(food: Food) {
    this.cart.update(currentCart => {
      const existingItem = currentCart.find(item => item.id === food.id);
      if (existingItem) {
        return currentCart.map(item =>
          item.id === food.id ? { ...item, quantity: item.quantity + 1 } : item
        );
      } else {
        return [...currentCart, { ...food, quantity: 1 }];
      }
    });
  }

  removeFromCart(foodId: number) {
    this.cart.update(currentCart => {
      return currentCart.filter(item => item.id !== foodId);
    });
  }

  toggleViewCart() {
    this.viewCart.update(currentView => !currentView);
  }
}