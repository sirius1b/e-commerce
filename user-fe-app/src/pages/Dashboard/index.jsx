// src/Dashboard.js
import React from "react";
import { Container, Card } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import { Footer, Header, NavBar } from "../../layout";
import { HeaderFooter } from "../../components";

function Dashboard() {
  return (
    <HeaderFooter>
      <Container className="mt-5">
        <Card className="p-4 shadow-sm text-center">
          <h2>Welcome to Your Dashboard!</h2>
          <p>
            This is where your personalized content would go after logging in.
          </p>
        </Card>
      </Container>
    </HeaderFooter>
  );
}

export default Dashboard;
