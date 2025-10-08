import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function Profile() {
  const navigate = useNavigate();
  const [member, setMember] = useState(null);
  const [form, setForm] = useState({ name: "", email: "", phone: "", password: "", bio: "" });
  const [message, setMessage] = useState("");

  useEffect(() => {
    const savedMember = localStorage.getItem("member");
    if (savedMember) {
      const m = JSON.parse(savedMember);
      setMember(m);
      setForm({ ...m, password: m.password || "" });
    } else {
      navigate("/login");
    }
  }, [navigate]);

  if (!member) return null;

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.put(`http://localhost:8080/members/${member.id}`, form);
      const updatedMember = res.data.data;
      localStorage.setItem("member", JSON.stringify(updatedMember));
      setMember(updatedMember);
      setMessage("✅ Profile updated successfully!");
    } catch (err) {
      setMessage("❌ Update failed: " + (err.response?.data?.message || "Try again"));
    }
  };

  return (
    <div className="min-vh-100 d-flex justify-content-center align-items-center bg-dark">
      <div className="card shadow-lg p-4" style={{ width: "28rem", borderRadius: "1.5rem", border: "2px solid #0f0", backgroundColor: "#121212" }}>
        <h3 className="text-center text-success mb-4">🎮 Update Profile</h3>

        {message && <div className="alert alert-info">{message}</div>}

        <form onSubmit={handleSubmit} className="text-light">
          <div className="mb-3">
            <label className="fw-bold">Name</label>
            <input type="text" name="name" className="form-control" value={form.name} onChange={handleChange} required />
          </div>
          <div className="mb-3">
            <label className="fw-bold">Email (cannot change)</label>
            <input type="email" name="email" className="form-control" value={form.email} disabled />
          </div>
          <div className="mb-3">
            <label className="fw-bold">Phone</label>
            <input type="text" name="phone" className="form-control" value={form.phone} onChange={handleChange} required />
          </div>
          <div className="mb-3">
            <label className="fw-bold">Password</label>
            <input type="password" name="password" className="form-control" value={form.password} onChange={handleChange} required />
          </div>
          <div className="mb-3">
            <label className="fw-bold">Bio</label>
            <textarea name="bio" className="form-control" value={form.bio || ""} onChange={handleChange} rows={3} />
          </div>

          <div className="d-grid gap-2 mt-3">
            <button className="btn btn-success">Update Profile</button>
            <button type="button" className="btn btn-outline-success" onClick={() => navigate("/dashboard")}>Back to Dashboard</button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default Profile;