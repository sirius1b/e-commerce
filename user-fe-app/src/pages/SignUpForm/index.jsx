import React, { useState } from "react";
import { Container, Form, Button, Alert, Card } from "react-bootstrap";
import { HeaderFooter } from "../../components";

function SignupForm() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [retypePassword, setRetypePassword] = useState("");
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const handleSubmit = (event) => {
    event.preventDefault();

    if (!name || !email || !password || !retypePassword) {
      setError("Please fill in all fields.");
      return;
    }

    if (password !== retypePassword) {
      setError("Passwords do not match.");
      return;
    }

    // Password validation regex (at least 8 characters, 1 uppercase, 1 lowercase, 1 number)
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;
    if (!passwordRegex.test(password)) {
      setError(
        "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, and one number."
      );
      return;
    }

    // Email validation regex
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
      setError("Please enter a valid email address.");
      return;
    }

    // Simulate successful signup
    setSuccess("Signup successful!");
    setError("");
    setName("");
    setEmail("");
    setPassword("");
    setRetypePassword("");
  };

  return (
    <HeaderFooter>
      <Container style={{ maxWidth: "500px", margin: "50px auto" }}>
        <Card className="p-4 shadow-sm">
          <h2>Sign Up</h2>
          {error && <Alert variant="danger">{error}</Alert>}
          {success && <Alert variant="success">{success}</Alert>}
          <Form onSubmit={handleSubmit}>
            <Form.Group className="mb-3" controlId="formName">
              <Form.Label>Name</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter name"
                value={name}
                onChange={(e) => setName(e.target.value)}
              />
            </Form.Group>

            <Form.Group className="mb-3" controlId="formEmail">
              <Form.Label>Email address</Form.Label>
              <Form.Control
                type="email"
                placeholder="Enter email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
            </Form.Group>

            <Form.Group className="mb-3" controlId="formPassword">
              <Form.Label>Password</Form.Label>
              <Form.Control
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </Form.Group>

            <Form.Group className="mb-3" controlId="formRetypePassword">
              <Form.Label>Retype Password</Form.Label>
              <Form.Control
                type="password"
                placeholder="Retype Password"
                value={retypePassword}
                onChange={(e) => setRetypePassword(e.target.value)}
              />
            </Form.Group>

            <Button variant="primary" type="submit">
              Sign Up
            </Button>
          </Form>
        </Card>
      </Container>
    </HeaderFooter>
  );
}

export default SignupForm;
