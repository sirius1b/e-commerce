// components/Footer.js
import React from "react";
import { Container } from "react-bootstrap";

const Footer = () => {
  return (
    <footer
      className="bg-dark text-light mt-auto py-3"
      style={{ marginTop: "auto" }}
    >
      <Container className="text-center">
        <span>
          &copy; {new Date().getFullYear()} My Website. All rights reserved.
        </span>
      </Container>
    </footer>
  );
};

export default Footer;
