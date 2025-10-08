import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";

function GameForm() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [form, setForm] = useState({ name: "", description: "", category: "", costPerMinute: "" });
  const [message, setMessage] = useState("");

  useEffect(() => {
    if (id) {
      axios.get(`http://localhost:8080/games/${id}`)
        .then(res => setForm(res.data.data))
        .catch(() => setMessage("❌ Failed to fetch game"));
    }
  }, [id]);

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (id) {
        await axios.put(`http://localhost:8080/games/${id}`, form);
        setMessage("✅ Game updated successfully");
      } else {
        await axios.post("http://localhost:8080/games", form);
        setMessage("✅ Game created successfully");
      }
      setTimeout(() => navigate("/games"), 1000);
    } catch {
      setMessage("❌ Operation failed");
    }
  };

  return (
    <div className="min-vh-100 d-flex align-items-center justify-content-center bg-light">
      <div className="card shadow-sm rounded-4 p-4 border-0 w-100" style={{ maxWidth: "600px" }}>
        <h3 className="text-success mb-4 text-center">{id ? "Edit Game" : "Create New Game"}</h3>
        {message && <div className="alert alert-info">{message}</div>}

        <form onSubmit={handleSubmit} className="row g-3">
          <div className="col-12">
            <label className="form-label fw-bold">Name</label>
            <input type="text" name="name" className="form-control" value={form.name} onChange={handleChange} required />
          </div>
          <div className="col-12">
            <label className="form-label fw-bold">Description</label>
            <textarea name="description" className="form-control" value={form.description} onChange={handleChange} rows={3} />
          </div>
          <div className="col-md-6">
            <label className="form-label fw-bold">Category</label>
            <input type="text" name="category" className="form-control" value={form.category} onChange={handleChange} />
          </div>
          <div className="col-md-6">
            <label className="form-label fw-bold">Cost per minute</label>
            <input type="number" step="0.01" name="costPerMinute" className="form-control" value={form.costPerMinute} onChange={handleChange} required />
          </div>
          <div className="col-12 d-flex justify-content-end mt-3">
            <button className="btn btn-success px-4">{id ? "Update Game" : "Create Game"}</button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default GameForm;