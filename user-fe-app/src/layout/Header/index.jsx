import React from "react";
import { Container } from "react-bootstrap";

export default function Header() {
  return (
    <header className="bg-primary text-white py-3">
      <Container>
        <h1 className="mb-0">My App</h1>
      </Container>
    </header>
  );
}
