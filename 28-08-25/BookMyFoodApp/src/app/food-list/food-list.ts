import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FoodService, Food } from '../food.service';

@Component({
  selector: 'app-food-list',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="grid grid-cols-1 gap-6 md:grid-cols-2 lg:grid-cols-3">
      @for (food of foodService.foods; track food.id) {
        <div class="bg-white rounded-lg shadow-lg overflow-hidden p-6 relative">
          <img [src]="food.image" [alt]="food.name" class="w-full h-40 object-cover rounded-md mb-4" />
          <h3 class="text-xl font-bold">{{ food.name }}</h3>
          <p class="text-lg font-semibold text-blue-600">\${{ food.price }}</p>
          <button
            (click)="foodService.addToCart(food)"
            class="mt-4 px-4 py-2 bg-blue-500 text-white font-semibold rounded-lg shadow-sm hover:bg-blue-600 transition duration-300"
          >
            Add to Cart
          </button>
        </div>
      }
    </div>
  `,
  styles: [],
})
export class FoodListComponent {
  foodService = inject(FoodService);
}