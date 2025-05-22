// src/LoginPage.js
import React, { useState } from 'react';
import { Form, Button, Container, Row, Col, Card } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css'; // Ensure Bootstrap CSS is imported

function LoginPage() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault(); // Prevent default form submission behavior

    // In a real application, you would send this data to your backend for authentication
    console.log('Login attempt:');
    console.log('Email:', email);
    console.log('Password:', password);

    // You can add logic here for:
    // - Sending data to an API
    // - Handling success (e.g., redirecting to a dashboard)
    // - Handling errors (e.g., displaying an error message)

    // Clear form fields after submission (optional)
    // setEmail('');
    // setPassword('');
  };

  return (
    <Container className="mt-5">
      <Row className="justify-content-md-center">
        <Col md={6} lg={4}>
          <Card className="p-4 shadow-sm">
            <h2 className="text-center mb-4">Login</h2>
            <Form onSubmit={handleSubmit}>
              <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label>Email address</Form.Label>
                <Form.Control
                  type="email"
                  placeholder="Enter email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  required // Makes the field mandatory
                />
              </Form.Group>

              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>Password</Form.Label>
                <Form.Control
                  type="password"
                  placeholder="Password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required // Makes the field mandatory
                />
              </Form.Group>

              <Button variant="primary" type="submit" className="w-100">
                Submit
              </Button>
            </Form>
          </Card>
        </Col>
      </Row>
    </Container>
  );
}

export default LoginPage;