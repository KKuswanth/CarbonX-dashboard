const API_BASE = "http://localhost:5000/api";

async function apiGet(path) {
  const res = await fetch(`${API_BASE}${path}`);
  if (!res.ok) {
    throw new Error(`API error ${res.status}`);
  }
  return res.json();
}

async function apiPost(path, body) {
  const res = await fetch(`${API_BASE}${path}`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(body),
  });
  const data = await res.json().catch(() => ({}));
  if (!res.ok) {
    throw new Error(data.error || `API error ${res.status}`);
  }
  return data;
}

function formatNumber(value) {
  return Number(value || 0).toLocaleString();
}

function loadNavbar() {
  return fetch("navbar.html")
    .then((res) => res.text())
    .then((html) => {
      const nav = document.getElementById("navbar");
      if (!nav) return;
      nav.innerHTML = html;
      const currentPage = location.pathname.split("/").pop() || "index.html";
      document.querySelectorAll("#navbar .nav-link").forEach((link) => {
        if (link.getAttribute("href") === currentPage) {
          link.classList.add("active");
        }
      });
    })
    .catch(() => {});
}
