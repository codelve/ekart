import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { ProductService } from '../product.service';
import { Cart } from './Cart';

@Component({
    templateUrl: 'cart.component.html',
    styleUrls: ['cart.component.css']
})
export class CartComponent {
    pageTitle = 'My Cart';
    selectedProducts: any = [];
    imageWidth = 50;
    imageMargin = 2;
    grandTotal: any = 0;
    quantity: any = 0;
    submit: boolean;

    constructor(private productService: ProductService,
        private _route: ActivatedRoute, private _router: Router) {
        this.submit = true;
        this.selectedProducts = this.productService.selectedProducts;
        for (let i = 0; i < this.selectedProducts.length; i++) {
            this.grandTotal += this.selectedProducts[i].totalPrice;
            this.quantity += this.selectedProducts[i].quantity;
        }
        sessionStorage.setItem('grandTotal', this.grandTotal);
        sessionStorage.setItem('selectedItems', this.quantity);
    }

    updateCart(product: Cart) {
        product.totalPrice = product.price * product.quantity;
        this.grandTotal = 0;
        this.quantity = 0;
        for (let i = 0; i < this.selectedProducts.length; i++) {
            this.grandTotal += this.selectedProducts[i].totalPrice;
            this.quantity += this.selectedProducts[i].quantity;
        }
        sessionStorage.setItem('selectedProducts', JSON.stringify(this.selectedProducts));
        sessionStorage.setItem('grandTotal', this.grandTotal);
        sessionStorage.setItem('selectedItems', this.quantity);
    }

    remove(index: number) {
        this.selectedProducts.splice(index, 1);
        this.grandTotal = 0;
        this.quantity = 0;
        for (let i = 0; i < this.selectedProducts.length; i++) {
            this.grandTotal += this.selectedProducts[i].totalPrice;
            this.quantity += this.selectedProducts[i].quantity;
        }
        sessionStorage.setItem('selectedProducts', JSON.stringify(this.selectedProducts));
        sessionStorage.setItem('grandTotal', this.grandTotal);
        sessionStorage.setItem('selectedItems', this.quantity);
    }

    onBack(): void {
        this._router.navigate(['/products', this.selectedProducts]);
    }

    checkout() {
        this.submit = false;
        this.productService.selectedProducts = [];
        sessionStorage.removeItem('selectedProducts');
    }

    gotoProducts() {
        sessionStorage.removeItem('grandTotal');
        sessionStorage.removeItem('selectedItems');
        this._router.navigate(['/products']);
    }
}
