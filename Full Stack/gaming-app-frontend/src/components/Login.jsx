import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Login() {
  const [form, setForm] = useState({ email: "", password: "" });
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      // POST login request
      const res = await axios.post("http://localhost:8080/members/login", form);
      const user = res.data.data;

      if (user) {
        // store logged-in member in localStorage
        localStorage.setItem("member", JSON.stringify(user));
        setMessage("✅ Login successful!");
        setTimeout(() => navigate("/dashboard"), 1000);
      }
    } catch (err) {
      // handle invalid login
      if (err.response) {
        setMessage("❌ " + err.response.data.message);
      } else {
        setMessage("❌ Error connecting to server");
      }
    }
  };

  return (
    <div className="d-flex justify-content-center align-items-center vh-100 bg-dark text-light">
      <div
        className="card p-4 shadow-lg"
        style={{ width: "25rem", backgroundColor: "#1a1a1a", borderRadius: "15px" }}
      >
        <h3 className="text-center mb-4 text-success">🎮 Gaming Club Login</h3>

        {message && <div className="alert alert-info">{message}</div>}

        <form onSubmit={handleSubmit} className="text-light">
          <div className="mb-3">
            <label>Email</label>
            <input
              type="email"
              name="email"
              className="form-control"
              value={form.email}
              onChange={handleChange}
              required
            />
          </div>
          <div className="mb-3">
            <label>Password</label>
            <input
              type="password"
              name="password"
              className="form-control"
              value={form.password}
              onChange={handleChange}
              required
            />
          </div>
          <button className="btn btn-success w-100">Login</button>
        </form>
      </div>
    </div>
  );
}

export default Login;