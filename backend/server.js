const express = require("express");
const fs = require("fs");
const cors = require("cors");
const path = require("path");

const app = express();
app.use(cors());
app.use(express.json());

// Load mock data
const dataFile = path.join(__dirname, "data.json");
let data = JSON.parse(fs.readFileSync(dataFile));

// Serve frontend files
app.use(express.static(path.join(__dirname, "../frontend")));

// ===== Dashboard =====
app.get("/api/dashboard", (req, res) => {
  res.json({
    activeProjects: data.activeProjects,
    carbonCredits: data.carbonCredits,
    mangroveArea: data.mangroveArea,
    communityImpact: data.communityImpact,
  });
});

// ===== Projects =====
app.get("/api/projects", (req, res) => {
  res.json(data.projects);
});

// ===== Transactions =====
app.get("/api/transactions", (req, res) => {
  res.json(data.transactions);
});

// ===== Monitoring =====
app.get("/api/monitoring", (req, res) => {
  res.json(data.monitoring);
});

// ===== Admin =====
app.get("/api/admin", (req, res) => {
  res.json(data.admin);
});

// ===== Marketplace API =====

// Buy credits
app.post("/api/marketplace/buy", (req, res) => {
  const { project, credits, price } = req.body;

  if (!project || !credits || !price) {
    return res.status(400).json({ error: "All fields are required" });
  }

  const transaction = {
    id: Date.now(),
    type: "BUY",
    project,
    credits: Number(credits),
    price: Number(price),
    date: new Date().toISOString(),
  };

  data.transactions.push(transaction);

  // Save back to file
  fs.writeFileSync(dataFile, JSON.stringify(data, null, 2));

  res.json({ message: "✅ Purchase successful!", transaction });
});

// Sell credits
app.post("/api/marketplace/sell", (req, res) => {
  const { project, credits, price } = req.body;

  if (!project || !credits || !price) {
    return res.status(400).json({ error: "All fields are required" });
  }

  const transaction = {
    id: Date.now(),
    type: "SELL",
    project,
    credits: Number(credits),
    price: Number(price),
    date: new Date().toISOString(),
  };

  data.transactions.push(transaction);

  // Save back to file
  fs.writeFileSync(dataFile, JSON.stringify(data, null, 2));

  res.json({ message: "✅ Sale successful!", transaction });
});

// ===== Default route → index.html =====
app.get("/", (req, res) => {
  res.sendFile(path.join(__dirname, "../frontend/index.html"));
});

// ===== Start Server =====
const PORT = 5000;
app.listen(PORT, () => {
  console.log(`✅ Server running at http://localhost:${PORT}`);
});
