// src/Home.js
import React from 'react';
import { Container, Card } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

/**
 * Renders the Home page content.
 * This is a placeholder component to demonstrate navigation.
 */
function Home() {
  return (
    <Container className="mt-5">
      <Card className="p-4 shadow-sm text-center">
        <h2>Welcome to the Home Page!</h2>
        <p>This is a simple home page to demonstrate navigation without React Router.</p>
      </Card>
    </Container>
  );
}

export default Home;
