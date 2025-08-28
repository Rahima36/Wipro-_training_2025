import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FoodService} from '../food.service';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="grid grid-cols-1 gap-6 md:grid-cols-2 lg:grid-cols-3">
      @if (foodService.cart().length > 0) {
        @for (item of foodService.cart(); track item.id) {
          <div class="bg-white rounded-lg shadow-lg overflow-hidden p-6 relative">
            <img [src]="item.image" [alt]="item.name" class="w-full h-40 object-cover rounded-md mb-4" />
            <div class="flex justify-between items-center mb-2">
              <h3 class="text-xl font-bold">{{ item.name }}</h3>
              <p class="text-lg font-semibold text-blue-600">\${{ item.price }}</p>
            </div>
            <p class="text-gray-600 mb-4">Quantity: {{ item.quantity }}</p>
            <div class="flex justify-end">
              <button (click)="foodService.removeFromCart(item.id)" class="px-3 py-1 bg-red-500 text-white text-sm rounded-md hover:bg-red-600 transition-colors duration-200">Remove</button>
            </div>
          </div>
        }
      } @else {
        <div class="col-span-full text-center text-gray-500 mt-12">
          <p>Your cart is empty.</p>
        </div>
      }
    </div>
  `,
  styles: [],
})
export class CartComponent {
  foodService = inject(FoodService);
}