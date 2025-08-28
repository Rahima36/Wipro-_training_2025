import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { FoodService } from '../food.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="flex items-center justify-center p-4">
      <div class="bg-white rounded-xl shadow-2xl p-8 w-full max-w-sm">
        <h2 class="text-3xl font-extrabold text-center text-gray-900 mb-6">Login</h2>
        <form (ngSubmit)="login()">
          <div class="mb-4">
            <input
              type="text"
              name="username"
              [(ngModel)]="username"
              placeholder="Username"
              class="w-full px-4 py-3 border border-gray-300 rounded-lg text-gray-700 placeholder-gray-500 focus:outline-none focus:ring-2 focus:ring-blue-500 transition duration-200"
            />
          </div>
          <div class="mb-6">
            <input
              type="password"
              name="password"
              [(ngModel)]="password"
              placeholder="Password"
              class="w-full px-4 py-3 border border-gray-300 rounded-lg text-gray-700 placeholder-gray-500 transition duration-200"
            />
          </div>
          <button
            type="submit"
            class="w-full bg-blue-600 hover:bg-blue-700 text-white font-semibold py-3 px-4 rounded-lg transition duration-300 ease-in-out transform hover:scale-105 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-opacity-50"
          >
            Login
          </button>
        </form>
      </div>
    </div>
  `,
  styles: [],
})
export class LoginComponent {
  foodService = inject(FoodService);
  username: string = '';
  password: string = '';

  login() {
    if (this.username && this.password) {
      this.foodService.login();
    }
  }
}