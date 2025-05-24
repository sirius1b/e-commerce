import React from "react";

import { HeaderFooter } from "../../components";
import nike1 from "@/assets/nike.avif"; // Placeholder image path
import nike2 from "@/assets/nike2.avif"; // Placeholder image path

const carts = [
  {
    id: 1,
    title: "Air Max Blaze",
    shortName: "Air Max",
    quantity: 1,
    price: 120.0,
    image: nike1,
  },
  {
    id: 2,
    title: "Air Max Blaze",
    shortName: "Air Max",
    quantity: 1,
    price: 120.0,
    image: nike2,
  },
];

function Cart({ cart = carts }) {
  //TODO
  return <HeaderFooter></HeaderFooter>;
}

export default Cart;
