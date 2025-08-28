import { Component, computed, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FoodService } from '../food.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule],
  template: `
    <header class="bg-white shadow-md p-4 flex items-center justify-between">
      <h1 class="text-2xl font-bold text-gray-900">BookMyFood</h1>
      @if (foodService.isLoggedIn()) {
        <div class="flex items-center space-x-4">
          <button (click)="foodService.toggleViewCart()" class="relative">
            <span class="text-gray-600 hover:text-blue-500 transition-colors duration-200 text-lg">
              <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 inline-block" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h2l.4 2M7 13h10l4-8H5.4M7 13L5.4 5M7 13l-1.6 7H15m-1-5l-4 4v2m0-6V7m0 2h3m-3 0h-3" />
              </svg>
            </span>
            @if (foodService.cartCount() > 0) {
              <span class="absolute -top-2 -right-2 bg-blue-500 text-white text-xs rounded-full h-5 w-5 flex items-center justify-center font-bold">{{ foodService.cartCount() }}</span>
            }
          </button>
          <button
            (click)="foodService.logout()"
            class="px-4 py-2 bg-red-600 text-white font-semibold rounded-lg shadow-sm hover:bg-red-700 transition duration-300"
          >
            Logout
          </button>
        </div>
      }
    </header>
  `,
  styles: [],
})
export class HeaderComponent {
  foodService = inject(FoodService);
}