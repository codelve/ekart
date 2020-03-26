import { OrderByPipe } from './orderby.pipe';

describe('OrderByPipe', () => {

  let pipe: OrderByPipe;
  let product1;
  let product2;
  let product3;
  let product4;

  beforeEach(() => {

    pipe = new OrderByPipe();
    
    product1 = {
      "productId": 1,
      "displayName": "Apple iPad Mini 2",
      "shortDesc": "TAB-120",
      "description": "16GB, White",
      "price": 19442,
      "imageUrl": "assets/imgs/apple_ipad_mini.jpg",
      "manufacturer": "Apple",
      "ostype": "iOS",
      "rating": 5
    };
    product2 = {
      "productId": 2,
      "displayName": "Apple iPad Air2",
      "shortDesc": "TAB-124",
      "description": "64GB, Black",
      "price": 44119,
      "imageUrl": "assets/imgs/ipad_air.jpg",
      "manufacturer": "Apple",
      "ostype": "iOS",
      "rating": 3
    };
    product3 = {
      "productId": 3,
      "displayName": "Samsung Galaxy J",
      "shortDesc": "TAB-125",
      "description": "8GB, Gold",
      "price": 13400,
      "imageUrl": "assets/imgs/samsung_tab.jpg",
      "manufacturer": "Samsung",
      "ostype": "Android",
      "rating": 4
    };
    product4 = {
      "productId": 4,
      "displayName": "Micromax Canvas P290",
      "shortDesc": "TAB-126",
      "description": "8GB, Black",
      "price": 3350,
      "imageUrl": "assets/imgs/tablet.jpg",
      "manufacturer": "Micromax",
      "ostype": "Android",
      "rating": 2
    };
  });

  it('create an instance', () => {
    expect(pipe).toBeTruthy();
  });
  
  it('should sort by popularity', () => {
    expect(pipe.transform([product1, product2, product3, product4], "popularity")).toEqual([product1, product3, product2, product4])

  });

  it('should sort by price from low to high', () => {
    expect(pipe.transform([product1, product2, product3, product4], "pricelh")).toEqual([product4, product3, product1, product2])

  });

  it('should sort by price from high to low', () => {
    expect(pipe.transform([product1, product2, product3, product4], "pricehl")).toEqual([product2, product1, product3, product4])
  });

});
