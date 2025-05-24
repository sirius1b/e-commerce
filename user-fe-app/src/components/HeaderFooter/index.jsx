import React from "react";
import { Footer, Header, NavBar } from "../../layout";

// ParentComponent.jsx
const HeaderFooter = (props) => {
  return (
    <>
      {/* <Header /> */}
      <NavBar />
      <div
        style={{ minHeight: "93vh", display: "flex", flexDirection: "column" }}
      >
        {props.children}
      </div>
      <Footer />
    </>
  );
};

export default HeaderFooter;
