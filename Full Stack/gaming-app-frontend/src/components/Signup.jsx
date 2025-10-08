import { useState } from "react";
import axios from "axios";

function Signup() {
  const [form, setForm] = useState({
    name: "",
    email: "",
    phone: "",
    password: "",
    bio: "",
  });
  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post("http://localhost:8080/members", form);
      setMessage("✅ Signup successful! You can now login.");
      setForm({ name: "", email: "", phone: "", password: "", bio: "" });
    } catch (err) {
      setMessage("❌ Signup failed: " + (err.response?.data?.message || "Try again"));
    }
  };

  return (
    <div className="d-flex justify-content-center align-items-center vh-100 bg-dark text-light">
      <div className="card p-4 shadow-lg" style={{ width: "25rem", backgroundColor: "#1a1a1a", borderRadius: "15px" }}>
        <h3 className="text-center mb-4 text-success">🎮 Gaming Club Signup</h3>
        {message && <div className="alert alert-info">{message}</div>}
        <form onSubmit={handleSubmit} className="text-light">
          <div className="mb-3">
            <label>Name</label>
            <input type="text" name="name" className="form-control" value={form.name} onChange={handleChange} required />
          </div>
          <div className="mb-3">
            <label>Email</label>
            <input type="email" name="email" className="form-control" value={form.email} onChange={handleChange} required />
          </div>
          <div className="mb-3">
            <label>Phone</label>
            <input type="text" name="phone" className="form-control" value={form.phone} onChange={handleChange} required />
          </div>
          <div className="mb-3">
            <label>Password</label>
            <input type="password" name="password" className="form-control" value={form.password} onChange={handleChange} required />
          </div>
          <div className="mb-3">
            <label>Bio</label>
            <textarea name="bio" className="form-control" value={form.bio} onChange={handleChange}></textarea>
          </div>
          <button className="btn btn-success w-100">Sign Up</button>
        </form>
      </div>
    </div>
  );
}

export default Signup;