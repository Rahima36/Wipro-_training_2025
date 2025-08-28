import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { FoodService } from './food.service';
import { HeaderComponent } from './header/header';
import { LoginComponent } from './log-in/log-in';
import { FoodListComponent } from './food-list/food-list';
import { CartComponent } from './cart/cart';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule, HeaderComponent, LoginComponent, FoodListComponent, CartComponent],
  template: `
    <div class="min-h-screen bg-gray-100 font-sans antialiased text-gray-800">
      <app-header />

      <main class="container mx-auto p-6">
        @if (!foodService.isLoggedIn()) {
          <app-login />
        } @else if (foodService.viewCart()) {
          <app-cart />
        } @else {
          <app-food-list />
        }
      </main>
    </div>
  `,
  styles: [
    `
      /* Additional custom styles can be added here */
    `,
  ],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class App {
  foodService = inject(FoodService);
}