import React from "react";
import { Container, Card } from "react-bootstrap";
import { Footer, NavBar } from "../../layout";
import { HeaderFooter } from "../../components";

export default function NotFound() {
  return (
    <HeaderFooter>
      <Container className="mt-5">
        <Card className="p-4 shadow-sm text-center">
          <h2>404 - Page Not Found</h2>
          <p>The page you are looking for does not exist.</p>
        </Card>
      </Container>
    </HeaderFooter>
  );
}
