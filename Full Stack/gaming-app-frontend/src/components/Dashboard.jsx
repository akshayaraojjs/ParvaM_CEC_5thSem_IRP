import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function Dashboard() {
  const navigate = useNavigate();
  const [member, setMember] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const savedMember = localStorage.getItem("member");
    if (!savedMember) {
      navigate("/login");
      return;
    }

    const memberId = JSON.parse(savedMember).id;

    const fetchMember = async () => {
      try {
        const res = await axios.get(`http://localhost:8080/members/${memberId}`);
        setMember(res.data.data);
        localStorage.setItem("member", JSON.stringify(res.data.data));
        setLoading(false);
      } catch (err) {
        console.error(err);
        navigate("/login");
      }
    };

    fetchMember();
  }, [navigate]);

  if (loading) return <p className="text-center mt-5 text-success">Loading...</p>;
  if (!member) return null;

  return (
    <div className="min-vh-100 d-flex justify-content-center align-items-center bg-dark">
      <div className="card text-light shadow-lg p-4" style={{ width: "24rem", borderRadius: "1.5rem", border: "2px solid #0f0", backgroundColor: "#121212" }}>
        <h3 className="text-center text-success mb-4">🎮 Member Dashboard</h3>

        <div className="mb-3">
          <h6 className="text-success fw-bold">Name</h6>
          <p>{member.name}</p>
        </div>
        <div className="mb-3">
          <h6 className="text-success fw-bold">Email</h6>
          <p>{member.email}</p>
        </div>
        <div className="mb-3">
          <h6 className="text-success fw-bold">Phone</h6>
          <p>{member.phone}</p>
        </div>
        <div className="mb-3">
          <h6 className="text-success fw-bold">Bio</h6>
          <p>{member.bio || "No bio available."}</p>
        </div>
        <div className="mb-3">
          <h6 className="text-success fw-bold">Balance</h6>
          <p className="fs-5">💰 {member.balance.toFixed(2)} credits</p>
        </div>

        <div className="d-grid gap-2">
          <button className="btn btn-success" onClick={() => navigate("/profile")}>Edit Profile</button>
          <button className="btn btn-outline-success" onClick={() => navigate("/")}>Back to Home</button>
        </div>
      </div>
    </div>
  );
}

export default Dashboard;