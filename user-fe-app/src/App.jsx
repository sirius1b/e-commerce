// src/App.js
import React from "react";
import { Navbar, Nav, Container } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";

// Import your page components
import LoginPage from "./pages/LoginPage";
import Home from "./pages/Home";
import Dashboard from "./pages/Dashboard";

/**
 * Main application component responsible for rendering different pages
 * using React Router for declarative navigation.
 */
function App() {
  return (
    <Router>
      {" "}
      {/* BrowserRouter wraps the entire application */}
      <div className="App">
        {/* Navigation Bar */}
        <Navbar bg="dark" variant="dark" expand="lg">
          <Container>
            {/* Use Link component for navigation within the app */}
            <Navbar.Brand as={Link} to="/">
              {" "}
              {/* 'as={Link}' ensures Navbar.Brand renders as a Link */}
              My App
            </Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
              <Nav className="me-auto">
                {/* Use Link components for navigation */}
                <Nav.Link as={Link} to="/">
                  Home
                </Nav.Link>{" "}
                {/* Link to the root path */}
                <Nav.Link as={Link} to="/dashboard">
                  Dashboard
                </Nav.Link>
                <Nav.Link as={Link} to="/login">
                  Login
                </Nav.Link>
              </Nav>
            </Navbar.Collapse>
          </Container>
        </Navbar>

        {/* Page Content - Routes define which component renders for each path */}
        <Routes>
          {/* Route for the Home page (default path) */}
          <Route path="/" element={<Home />} />
          {/* Route for the Dashboard page */}
          <Route path="/dashboard" element={<Dashboard />} />
          {/* Route for the Login page */}
          <Route path="/login" element={<LoginPage />} />
          {/* You can add a catch-all route for 404 pages if needed */}
          {/* <Route path="*" element={<div>404 Not Found</div>} /> */}
        </Routes>
      </div>
    </Router>
  );
}

export default App;
