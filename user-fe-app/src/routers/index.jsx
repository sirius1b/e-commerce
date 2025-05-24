import { Routes, Route, BrowserRouter } from "react-router-dom";

import { Home, LoginPage, Dashboard, NotFound, SignUpForm } from "@/pages";
import { Cataloge } from "../pages";

const Routers = () => {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/signup" element={<SignUpForm />} />
          <Route path="/products" element={<Cataloge />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
};

export default Routers;
