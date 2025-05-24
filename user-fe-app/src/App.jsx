// src/App.js
import React from "react";
import { Navbar, Nav, Container } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import Routers from "./routers";

/**
 * Main application component responsible for rendering different pages
 * using React Router for declarative navigation.
 */
function App() {
  return (
    <>
      <Routers />
    </>
  );
}

export default App;
