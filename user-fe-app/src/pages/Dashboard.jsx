// src/Dashboard.js
import React from 'react';
import { Container, Card } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

/**
 * Renders the Dashboard page content.
 * This is a placeholder component to demonstrate navigation.
 */
function Dashboard() {
  return (
    <Container className="mt-5">
      <Card className="p-4 shadow-sm text-center">
        <h2>Welcome to Your Dashboard!</h2>
        <p>This is where your personalized content would go after logging in.</p>
      </Card>
    </Container>
  );
}

export default Dashboard;
