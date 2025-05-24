import React from "react";
import Card from "react-bootstrap/Card";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";
import { HeaderFooter } from "../../components";
import { Container, Button } from "react-bootstrap";
import nike1 from "@/assets/nike.avif"; // Placeholder image path
import nike2 from "@/assets/nike2.avif"; // Placeholder image path
import nike3 from "@/assets/nike3.avif"; // Placeholder image path

const cards = [
  {
    id: 1,
    title: "Air Max Blaze",
    description:
      "Experience all-day comfort with the latest Air Max Blaze, featuring responsive cushioning and a modern design.",
    image: nike1,
    price: "$120.00",
    actualPrice: "$150.00",
    limited: false,
    cart: false,
  },
  {
    id: 2,
    title: "Retro High Runner",
    description:
      "Classic meets comfort in these Retro High Runners. Perfect for casual wear or light workouts.",
    image: nike2,
    price: "$85.00",
    actualPrice: "$100.00",
    limited: false,
    cart: false,
  },
  {
    id: 3,
    title: "Urban Trek Pro",
    description:
      "Designed for the city explorer. Durable, supportive, and sleek for everyday use.",
    image: nike3,
    price: "$130.00",
    actualPrice: "$160.00",
    limited: true,
    cart: false,
  },
  {
    id: 4,
    title: "Velocity Zoom",
    description:
      "Take off with Velocity Zoom’s ultra-light sole and breathable mesh. Speed meets style.",
    image: nike3,
    price: "$110.00",
    actualPrice: "$140.00",
    limited: true,
    cart: false,
  },
  {
    id: 22,
    title: "Desert Track X",
    description:
      "All-terrain sneaker boots built for resilience. Stay grounded no matter the surface.",
    image: nike3,
    price: "$140.00",
    actualPrice: "$175.00",
    limited: true,
    cart: false,
  },
  {
    id: 33,
    title: "WaveForm Glide",
    description:
      "Smooth ride and standout design. Ideal for long walks and running errands.",
    image: nike3,
    price: "$95.00",
    actualPrice: "$120.00",
    limited: true,
    cart: false,
  },
  {
    id: 11,
    title: "Echo Sprint",
    description:
      "Push your limits with Echo Sprint. Engineered for performance with style in mind.",
    image: nike3,
    price: "$125.00",
    actualPrice: "$150.00",
    limited: true,
    cart: true,
  },
  {
    id: 12,
    title: "Lunar Step 5",
    description:
      "A futuristic fit with plush cushioning for ultimate comfort in motion.",
    image: nike3,
    price: "$105.00",
    actualPrice: "$135.00",
    limited: true,
    cart: true,
  },
  {
    id: 1233,
    title: "CoreFlex Prime",
    description:
      "Flexibility meets durability in the CoreFlex Prime. Built for the gym or the street.",
    image: nike3,
    price: "$98.00",
    actualPrice: "$125.00",
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
