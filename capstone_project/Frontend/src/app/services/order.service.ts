
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class OrderService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  placeOrder(order: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/order`, order);
  }


  // ✅ Fetch orders for a specific user
  getOrders(userId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/order/${userId}`);
  }
}
