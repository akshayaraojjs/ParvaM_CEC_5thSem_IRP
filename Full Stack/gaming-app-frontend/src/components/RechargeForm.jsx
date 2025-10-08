import { useState, useEffect } from "react";
import axios from "axios";

function RechargeForm() {
  const [form, setForm] = useState({ memberId: "", amount: "" });
  const [members, setMembers] = useState([]);
  const [message, setMessage] = useState("");

  // Fetch members from backend
  useEffect(() => {
    const fetchMembers = async () => {
      try {
        const res = await axios.get("http://localhost:8080/members");
        setMembers(res.data.data); // assuming response structure { data: [...] }
      } catch (err) {
        setMessage("❌ Error fetching members");
      }
    };
    fetchMembers();
  }, []);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!form.memberId) {
      setMessage("❌ Please select a member");
      return;
    }
    try {
      const res = await axios.post("http://localhost:8080/recharges", form);
      setMessage(`✅ ${res.data.message}`);
      setForm({ memberId: "", amount: "" });
    } catch (err) {
      setMessage("❌ Error adding recharge");
    }
  };

  return (
    <div className="container min-vh-100 mt-5">
      <h3 className="text-success mb-4">💰 Recharge Member Balance</h3>
      {message && <div className="alert alert-info">{message}</div>}
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label>Member</label>
          <select
            name="memberId"
            className="form-select"
            value={form.memberId}
            onChange={handleChange}
            required
          >
            <option value="">-- Select Member --</option>
            {members.map((m) => (
              <option key={m.id} value={m.id}>
                {m.name} ({m.email})
              </option>
            ))}
          </select>
        </div>

        <div className="mb-3">
          <label>Amount</label>
          <input
            type="number"
            name="amount"
            className="form-control"
            value={form.amount}
            onChange={handleChange}
            required
          />
        </div>

        <button className="btn btn-success">Recharge</button>
      </form>
    </div>
  );
}

export default RechargeForm;