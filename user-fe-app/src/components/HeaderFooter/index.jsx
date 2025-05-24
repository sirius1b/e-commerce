import React from "react";
import { Footer, Header, NavBar } from "../../layout";

// ParentComponent.jsx
const HeaderFooter = (props) => {
  return (
    <>
      <Header />
      <NavBar />
      {props.children}
      <Footer />
    </>
  );
};

export default HeaderFooter;
