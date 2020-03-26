import { HttpClient } from '@angular/common/http';
import { Injectable, OnDestroy } from '@angular/core';
import { Observable } from 'rxjs';
import { InformationService } from '../_services/information.service';
import { Product } from './product';


@Injectable()
export class ProductService implements OnDestroy {
    selectedProducts: any = [];
    products: any = [];
    category = 'tablet';
    email: string;
    constructor(private informationService: InformationService, private http: HttpClient) {
        if (sessionStorage.getItem('selectedProducts')) {
            this.selectedProducts = JSON.parse(sessionStorage.getItem('selectedProducts'));
        }
    }

    getProducts(): Observable<Array<Product>> {
        if (this.category === 'tablet') {
            return this.http.get<Array<Product>>(this.informationService.tabletProductUrl);
        } else if (this.category === 'mobile') {
            return this.http.get<Array<Product>>(this.informationService.moblieProductUrl);
        }
    }

    getProduct(id: number): Observable<Array<Product>> {
        return this.http.get<Array<Product>>(this.informationService.productUrl + id);
    }

    ngOnDestroy(): void {
        this.products = [];
        this.selectedProducts = [];
    }
}
