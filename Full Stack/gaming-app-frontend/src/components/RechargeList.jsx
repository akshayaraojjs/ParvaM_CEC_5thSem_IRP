import { useState, useEffect } from "react";
import axios from "axios";

function RechargeList() {
  const [recharges, setRecharges] = useState([]);
  const [message, setMessage] = useState("");

  useEffect(() => {
    fetchRecharges();
  }, []);

  const fetchRecharges = async () => {
    try {
      const res = await axios.get("http://localhost:8080/recharges");
      setRecharges(res.data.data);
    } catch (err) {
      setMessage("❌ Error fetching recharges");
    }
  };

  return (
    <div className="container min-vh-100 mt-5">
      <h3 className="text-success mb-4">💳 Recharge History</h3>
      {message && <div className="alert alert-danger">{message}</div>}
      <table className="table table-dark table-striped">
        <thead>
          <tr>
            <th>ID</th>
            <th>Member</th>
            <th>Amount</th>
            <th>Updated Balance</th>
            <th>Recharge At</th>
          </tr>
        </thead>
        <tbody>
          {recharges.map((r) => (
            <tr key={r.id}>
              <td>{r.id}</td>
              <td>{r.memberName}</td>
              <td>{r.amount}</td>
              <td>{r.updatedBalance}</td>
              <td>{new Date(r.rechargeAt).toLocaleString()}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default RechargeList;