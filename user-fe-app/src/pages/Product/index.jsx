import React, { useState } from "react";
import {
  Container,
  Row,
  Col,
  Image,
  Button,
  Form,
  Card,
} from "react-bootstrap";
// @ts-ignore
import nike1 from "../../assets/nike.avif";
// @ts-ignore
import nike2 from "../../assets/nike2.avif";
// @ts-ignore
import nike3 from "../../assets/nike3.avif";
import { HeaderFooter } from "../../components";

const product = {
  id: 1,
  brand: "Nike",
  name: "Nike Air Max 270",
  images: [nike1, nike2, nike3],
  price: 150.0,
  mrp: 200.0,
  description:
    "The Nike Air Max 270 is a stylish and comfortable sneaker designed for everyday wear. It features a large Air unit in the heel for cushioning, a breathable mesh upper, and a sleek silhouette that pairs well with any outfit.",
  rating: 4.5,
  numReviews: 125,
  features: [
    "Large Air unit for cushioning",
    "Breathable mesh upper",
    "Sleek and stylish design",
    "Available in multiple colors",
  ],
  deliveryInfo: "Free delivery by Monday, May 27",
  available: true,
  aboutBrand:
    "Nike is a global leader in athletic footwear and apparel, known for its innovative designs and commitment to performance. The Air Max 270 is part of their lifestyle collection, blending sport and fashion seamlessly.",
};

const similarProduct = [
  {
    id: 1,
    title: "Air Max Blaze",
    description:
      "Experience all-day comfort with the latest Air Max Blaze, featuring responsive cushioning and a modern design.",
    image: nike1,
    price: "$120.00",
    actualPrice: "$150.00",
    limited: false,
    cart: false,
  },
  {
    id: 2,
    title: "Retro High Runner",
    description:
      "Classic meets comfort in these Retro High Runners. Perfect for casual wear or light workouts.",
    image: nike2,
    price: "$85.00",
    actualPrice: "$100.00",
    limited: false,
    cart: false,
  },
  {
    id: 3,
    title: "Urban Trek Pro",
    description:
      "Designed for the city explorer. Durable, supportive, and sleek for everyday use.",
    image: nike3,
    price: "$130.00",
    actualPrice: "$160.00",
    limited: true,
    cart: false,
  },
  {
    id: 4,
    title: "Velocity Zoom",
    description:
      "Take off with Velocity Zoom’s ultra-light sole and breathable mesh. Speed meets style.",
    image: nike3,
    price: "$110.00",
    actualPrice: "$140.00",
    limited: true,
    cart: false,
  },
];

const styles = {
  productPageContainer: {
    fontFamily: "inherit",
    color: "#1a1a1a",
    marginTop: "2rem",
    marginBottom: "2rem",
  },
  thumbnailColumn: {
    display: "flex",
    flexDirection: "column",
    gap: "10px",
  },
  thumbnailWrapper: {
    border: "1.5px solid #e3e6e8",
    borderRadius: "6px",
    cursor: "pointer",
    overflow: "hidden",
    marginBottom: "8px",
    transition: "border-color 0.2s",
    background: "#fff",
  },
  selectedThumbnail: {
    borderColor: "#1976d2",
    boxShadow: "0 0 0 2px #1976d2",
  },
  thumbnailImage: {
    width: "60px",
    height: "60px",
    objectFit: "cover",
    display: "block",
  },
  mainImageColumn: {
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    border: "1.5px solid #e3e6e8",
    borderRadius: "6px",
    background: "#fff",
    minHeight: "340px",
    padding: "10px",
  },
  mainProductImage: {
    maxWidth: "100%",
    maxHeight: "320px",
    objectFit: "contain",
    display: "block",
  },
  productBrand: {
    color: "#1976d2",
    fontSize: "0.95rem",
    textTransform: "uppercase",
    marginBottom: "5px",
    fontWeight: 600,
  },
  productTitle: {
    fontSize: "1.6rem",
    fontWeight: "600",
    marginBottom: "8px",
  },
  productRating: {
    color: "#1976d2",
    fontSize: "1rem",
    marginBottom: "6px",
  },
  strikethrough: {
    textDecoration: "line-through",
    color: "#565959",
    marginRight: "8px",
    fontSize: "1.1rem",
  },
  currentPrice: {
    fontSize: "1.7rem",
    color: "#1976d2",
    fontWeight: "bold",
    marginBottom: "5px",
  },
  discountInfo: {
    color: "#067d62",
    fontWeight: "bold",
    fontSize: "1rem",
    marginBottom: "5px",
  },
  inclusiveTaxes: {
    fontSize: "0.9rem",
    color: "#565959",
    marginBottom: "10px",
  },
  productAbout: {
    marginTop: "18px",
  },
  productAboutH3: {
    fontSize: "1.1rem",
    marginTop: "8px",
    marginBottom: "8px",
    fontWeight: "600",
  },
  productAboutUl: {
    listStyleType: "disc",
    marginLeft: "20px",
    paddingLeft: "0",
    marginBottom: "10px",
  },
  addToCartCard: {
    border: "1.5px solid #e3e6e8",
    borderRadius: "8px",
    boxShadow: "0 2px 8px rgba(25, 118, 210, 0.06)",
    background: "#f8fafc",
    padding: "18px 12px",
  },
  priceCardText: {
    fontSize: "1.2rem",
    marginBottom: "10px",
    color: "#1976d2",
    fontWeight: "bold",
  },
  deliveryInfo: {
    fontSize: "0.95rem",
    color: "#1976d2",
    marginBottom: "8px",
  },
  availability: {
    fontWeight: "bold",
    marginBottom: "10px",
    color: "#067d62",
  },
  outOfStock: {
    color: "#cc0c39",
    fontWeight: "bold",
    marginBottom: "10px",
  },
  quantitySelect: {
    width: "80px",
    display: "inline-block",
    marginLeft: "5px",
  },
  addToCartButton: {
    backgroundColor: "#1976d2",
    borderColor: "#1976d2",
    color: "#fff",
    width: "100%",
    fontWeight: "500",
    padding: "10px 0",
    marginBottom: "8px",
    marginTop: "8px",
    fontSize: "1rem",
  },
  buyNowButton: {
    backgroundColor: "#fff",
    borderColor: "#1976d2",
    color: "#1976d2",
    width: "100%",
    fontWeight: "500",
    padding: "10px 0",
    fontSize: "1rem",
    marginBottom: "8px",
  },
};

function Product() {
  const [mainImage, setMainImage] = useState(product.images[0]);
  const [quantity, setQuantity] = useState(1);

  // Carousel state
  const [carouselIndex, setCarouselIndex] = useState(0);
  const visibleCards = 3;
  const total = similarProduct.length;

  const handlePrev = () => {
    setCarouselIndex((prev) => (prev - 1 + total) % total);
  };
  const handleNext = () => {
    setCarouselIndex((prev) => (prev + 1) % total);
  };

  // Get visible cards for carousel
  const getVisibleCards = () => {
    let cards = [];
    for (let i = 0; i < visibleCards; i++) {
      const idx = (carouselIndex + i) % total;
      cards.push(similarProduct[idx]);
    }
    return cards;
  };

  return (
    <HeaderFooter>
      <Container style={styles.productPageContainer}>
        <Row>
          {/* Left: Thumbnails + Main Image */}
          <Col md={5}>
            <Row>
              <Col xs={2} style={styles.thumbnailColumn}>
                {product.images.map((img, idx) => (
                  <div
                    key={idx}
                    style={{
                      ...styles.thumbnailWrapper,
                      ...(mainImage === img ? styles.selectedThumbnail : {}),
                    }}
                    onClick={() => setMainImage(img)}
                  >
                    <Image
                      src={img}
                      alt={`Thumbnail ${idx + 1}`}
                      style={styles.thumbnailImage}
                      fluid
                    />
                  </div>
                ))}
              </Col>
              <Col xs={10} style={styles.mainImageColumn}>
                <Image
                  src={mainImage}
                  alt={product.name}
                  style={styles.mainProductImage}
                  fluid
                />
              </Col>
            </Row>
          </Col>
          {/* Middle: Product Details */}
          <Col md={4}>
            <div>
              <div style={styles.productBrand}>{product.brand}</div>
              <div style={styles.productTitle}>{product.name}</div>
              <div style={styles.productRating}>
                ★ {product.rating}{" "}
                <span style={{ color: "#565959" }}>
                  ({product.numReviews} ratings)
                </span>
              </div>
              <hr />
              <div>
                <span style={styles.strikethrough}>
                  ₹{product.mrp.toFixed(2)}
                </span>
                <span style={styles.currentPrice}>
                  ₹{product.price.toFixed(2)}
                </span>
              </div>
              <div style={styles.discountInfo}>
                You Save: ₹{(product.mrp - product.price).toFixed(2)} (
                {(((product.mrp - product.price) / product.mrp) * 100).toFixed(
                  0
                )}
                %)
              </div>
              <div style={styles.inclusiveTaxes}>Inclusive of all taxes</div>
              <hr />
              <div style={styles.productAbout}>
                <div style={{ fontSize: "0.97rem", color: "#333" }}>
                  {product.description}
                </div>
                <h3 style={styles.productAboutH3}>Features</h3>
                <ul style={styles.productAboutUl}>
                  {product.features.map((f, i) => (
                    <li key={i}>{f}</li>
                  ))}
                </ul>
              </div>
              <div
                style={{
                  marginTop: "18px",
                  // color: "#1976d2",
                  fontSize: "0.97rem",
                }}
              >
                <u>
                  <b>Brand Info</b>
                </u>
                <br />
                {product.aboutBrand}
              </div>
            </div>
          </Col>
          {/* Right: Add to Cart */}
          <Col md={3}>
            <Card style={styles.addToCartCard}>
              <Card.Body>
                <Card.Text style={styles.priceCardText}>
                  ₹{product.price.toFixed(2)}
                </Card.Text>
                <Card.Text style={styles.deliveryInfo}>
                  <i className="bi bi-truck"></i> {product.deliveryInfo}
                </Card.Text>
                <Card.Text
                  style={
                    product.available ? styles.availability : styles.outOfStock
                  }
                >
                  {product.available ? "In stock." : "Currently unavailable."}
                </Card.Text>
                {product.available && (
                  <>
                    <Form.Group controlId="quantitySelect" className="mb-3">
                      <Form.Label>Quantity:</Form.Label>
                      <Form.Control
                        as="select"
                        value={quantity}
                        onChange={(e) => setQuantity(Number(e.target.value))}
                        style={styles.quantitySelect}
                      >
                        {[...Array(10).keys()].map((x) => (
                          <option key={x + 1} value={x + 1}>
                            {x + 1}
                          </option>
                        ))}
                      </Form.Control>
                    </Form.Group>
                    <Button style={styles.addToCartButton}>Add to Cart</Button>
                    <Button
                      style={styles.buyNowButton}
                      variant="outline-primary"
                    >
                      Buy Now
                    </Button>
                  </>
                )}
              </Card.Body>
            </Card>
          </Col>
        </Row>
        <div style={{ marginTop: "3rem" }}>
          <h4 style={{ color: "#1976d2", marginBottom: "1rem" }}>
            Similar Products
          </h4>
          <div
            style={{
              display: "flex",
              alignItems: "center",
              gap: "1rem",
              justifyContent: "center",
            }}
          >
            <Button
              variant="outline-primary"
              size="sm"
              onClick={handlePrev}
              style={{ height: "40px" }}
            >
              &#8592;
            </Button>
            <div
              style={{
                display: "flex",
                gap: "1.5rem",
                flexWrap: "nowrap",
                overflow: "hidden",
                minWidth: "0",
              }}
            >
              {getVisibleCards().map((p, idx) => (
                <Card
                  key={p.id}
                  style={{
                    width: "16rem",
                    minWidth: "16rem",
                    boxShadow: "0 4px 8px rgba(0,0,0,0.08)",
                  }}
                >
                  <Card.Img
                    variant="top"
                    src={p.image}
                    style={{ height: "180px", objectFit: "cover" }}
                  />
                  <Card.Body>
                    <Card.Title style={{ fontSize: "1.1rem" }}>
                      {p.title}
                    </Card.Title>
                    <Card.Text style={{ fontSize: "0.95rem" }}>
                      {p.description.length > 50
                        ? p.description.slice(0, 50) + "..."
                        : p.description}
                      <br />
                      <del style={{ color: "#888" }}>{p.actualPrice}</del>{" "}
                      <span style={{ color: "#1976d2", fontWeight: 600 }}>
                        {p.price}
                      </span>
                      <br />
                      <div style={{ minHeight: "1.5rem" }}>
                        {p.limited && (
                          <span
                            className="text-danger"
                            style={{ fontSize: "0.9rem" }}
                          >
                            Limited Time Offer!
                          </span>
                        )}{" "}
                      </div>
                    </Card.Text>
                    <Button
                      variant={p.cart ? "danger" : "primary"}
                      size="sm"
                      style={{ width: "100%" }}
                    >
                      {p.cart ? "Remove" : "Add to Cart"}
                    </Button>
                  </Card.Body>
                </Card>
              ))}
            </div>
            <Button
              variant="outline-primary"
              size="sm"
              onClick={handleNext}
              style={{ height: "40px" }}
            >
              &#8594;
            </Button>
          </div>
        </div>
      </Container>
    </HeaderFooter>
  );
}

export default Product;
