import { Component, OnInit } from '@angular/core';
import { OrderService } from '../services/order.service';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-checkout',
  template: `
    <div class="checkout-container">
      <h2 class="title">Checkout</h2>

      <div *ngIf="cartItems.length === 0" class="empty-cart">
        <p>Your cart is empty.</p>
      </div>

      <div *ngIf="cartItems.length > 0">
        <table class="cart-table">
          <thead>
            <tr>
              <th>Product</th>
              <th>Qty</th>
              <th>Price</th>
            </tr>
          </thead>
          <tbody>
            <tr *ngFor="let item of cartItems">
              <td>{{ item.name }}</td>
              <td>{{ item.quantity }}</td>
              <td>₹ {{ item.price * item.quantity }}</td>
            </tr>
          </tbody>
        </table>

        <div class="total">
          <strong>Total:</strong> ₹ {{ total }}
        </div>

        <div class="form-group">
          <label for="address">Shipping Address</label>
          <textarea
            id="address"
            [(ngModel)]="address"
            placeholder="Enter your address"
          ></textarea>
        </div>

        <button (click)="placeOrder()" class="btn-submit">Place Order</button>
      </div>
    </div>
  `,
  styles: [`
    .checkout-container {
      max-width: 800px;
      margin: 30px auto;
      padding: 20px;
      background: #fff;
      border-radius: 10px;
      box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    }

    .title {
      font-size: 1.8rem;
      margin-bottom: 20px;
      font-weight: bold;
      text-align: center;
      color: #1e3a8a;
    }

    .empty-cart {
      text-align: center;
      font-size: 1.2rem;
      color: gray;
    }

    .cart-table {
      width: 100%;
      border-collapse: collapse;
      margin-bottom: 20px;
    }

    .cart-table th,
    .cart-table td {
      padding: 10px;
      border-bottom: 1px solid #ddd;
    }

    .total {
      font-size: 1.2rem;
      margin-bottom: 15px;
      text-align: right;
    }

    .form-group {
      margin-bottom: 15px;
    }

    .form-group label {
      font-weight: bold;
      display: block;
      margin-bottom: 5px;
    }

    .form-group textarea {
      width: 100%;
      padding: 8px;
      border-radius: 6px;
      border: 1px solid #ccc;
      font-size: 1rem;
    }

    .btn-submit {
      background: #1e3a8a;
      color: white;
      border: none;
      padding: 10px 20px;
      border-radius: 8px;
      font-size: 1rem;
      cursor: pointer;
      transition: 0.3s;
      display: block;
      margin: auto;
    }

    .btn-submit:hover {
      background: #2563eb;
    }
  `]
})
export class CheckoutComponent implements OnInit {
  user: any;
  cartItems: any[] = [];
  total: number = 0;
  address: string = '';

  constructor(
    private orderService: OrderService,
    private auth: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    this.user = this.auth.getUser();

    // TODO: Replace this dummy data with cart API fetch
    this.cartItems = [
      { name: 'Product A', quantity: 2, price: 500 },
      { name: 'Product B', quantity: 1, price: 1200 }
    ];

    this.total = this.cartItems.reduce(
      (sum, item) => sum + item.price * item.quantity,
      0
    );
  }

  placeOrder() {
    if (!this.user || !this.user.id) {
      alert('You must be logged in to place an order');
      return;
    }

    if (!this.address.trim()) {
      alert('Please enter a shipping address');
      return;
    }

    const order = {
      userId: this.user.id,
      items: this.cartItems,
      total: this.total,
      address: this.address
    };

    this.orderService.placeOrder(order).subscribe({
      next: res => {
        console.log('Order placed', res);
        this.cartItems = [];
        this.router.navigate(['/my-orders']);
      },
      error: err => console.error('Checkout failed', err)
    });
  }
}
