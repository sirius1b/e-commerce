import React from "react";
import Card from "react-bootstrap/Card";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";
import { HeaderFooter } from "../../components";
import { Container, Button } from "react-bootstrap";
import image from "@/assets/image.jpg"; // Placeholder image path

const cards = [
  {
    id: 1,
    title: "Card 1",
    description:
      "This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.",
    image: image,
    price: "$10.00",
    actualPrice: "$15.00",
    limited: false,
    cart: false,
  },
  {
    id: 2,
    title: "Card 1",
    description:
      "This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.",
    image: image,
    price: "$10.00",
    actualPrice: "$15.00",
    limited: false,
    cart: false,
  },
  {
    id: 3,
    title: "Card 1",
    description:
      "This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.",
    image: image,
    price: "$10.00",
    actualPrice: "$15.00",
    limited: true,
    cart: false,
  },
  {
    id: 4,
    title: "Card 1",
    description:
      "This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.",
    image: image,
    price: "$10.00",
    actualPrice: "$15.00",
    limited: true,
    cart: false,
  },
  {
    id: 22,
    title: "Card 1",
    description:
      "This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.",
    image: image,
    price: "$10.00",
    actualPrice: "$15.00",
    limited: true,
    cart: false,
  },
  {
    id: 33,
    title: "Card 1",
    description:
      "This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.",
    image: image,
    price: "$10.00",
    actualPrice: "$15.00",
    limited: true,
    cart: false,
  },
  {
    id: 11,
    title: "Card 1",
    description:
      "This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.",
    image: image,
    price: "$10.00",
    actualPrice: "$15.00",
    limited: true,
    cart: true,
  },
  {
    id: 12,
    title: "Card 1",
    description:
      "This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.",
    image: image,
    price: "$10.00",
    actualPrice: "$15.00",
    limited: true,
    cart: true,
  },
  {
    id: 1233,
    title: "Card 1",
    description:
      "This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.",
    image: image,
    price: "$10.00",
    actualPrice: "$15.00",
    limited: true,
    cart: false,
  },
];

const shortenText = (text, maxLength = 100) => {
  if (text.length <= maxLength) return text;
  return text.slice(0, maxLength) + "...";
};
function Cataloge({ products = cards }) {
  return (
    <HeaderFooter>
      <div className="m-5">
        <div
          style={{
            display: "flex",
            flexWrap: "wrap",
            gap: "1.5rem",
            justifyContent: "flex-start",
          }}
        >
          {products.map((p, idx) => (
            <div key={idx}>
              <Card
                style={{
                  width: "18rem",
                  marginBottom: "1.5rem",
                  boxShadow: "0 4px 8px rgba(0,0,0,0.1)",
                }}
              >
                <Card.Img
                  variant="top"
                  src={p.image}
                  style={{ height: "300px", width: "auto" }}
                />
                <Card.Body>
                  <Card.Title>{p.title}</Card.Title>
                  <Card.Text>
                    {shortenText(p.description)}
                    <br />
                    <del>{p.actualPrice}</del>

                    <strong>{p.price}</strong>
                    <br />

                    <div style={{ minHeight: "1.5rem" }}>
                      {p.limited && (
                        <span className="text-danger">
                          {" "}
                          Limited Time Offer!
                        </span>
                      )}{" "}
                    </div>
                    {!p.cart && <Button variant="primary">Add to Cart</Button>}
                    {p.cart && <Button variant="danger">Remove</Button>}
                  </Card.Text>
                </Card.Body>
              </Card>
            </div>
          ))}
        </div>
      </div>
    </HeaderFooter>
  );
}

export default Cataloge;

/*
import Card from 'react-bootstrap/Card';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';

function GridExample() {
  return (
    <Row xs={1} md={2} className="g-4">
      {Array.from({ length: 4 }).map((_, idx) => (
        <Col key={idx}>
          <Card>
            <Card.Img variant="top" src="holder.js/100px160" />
            <Card.Body>
              <Card.Title>Card title</Card.Title>
              <Card.Text>
                This is a longer card with supporting text below as a natural
                lead-in to additional content. This content is a little bit
                longer.
              </Card.Text>
            </Card.Body>
          </Card>
        </Col>
      ))}
    </Row>
  );
}

export default GridExample;
*/
