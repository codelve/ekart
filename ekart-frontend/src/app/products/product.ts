export class Product {
    constructor(public productId: number,
        public displayName: string,
        public shortDesc: string,
        public price: number,
        public description: string,
        public imageUrl: string, public manufacturer: string) {
    }
}
