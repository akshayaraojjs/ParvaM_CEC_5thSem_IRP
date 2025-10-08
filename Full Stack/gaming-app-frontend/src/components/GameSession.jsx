import { useEffect, useState } from "react";
import axios from "axios";

function GameSession() {
    const [members, setMembers] = useState([]);
    const [games, setGames] = useState([]);
    const [sessions, setSessions] = useState([]);
    const [form, setForm] = useState({ memberId: "", gameId: "" });
    const [message, setMessage] = useState("");

    useEffect(() => {
        fetchMembers();
        fetchGames();
        fetchSessions();

        const interval = setInterval(() => {
            fetchSessions();
        }, 30000);

        return () => clearInterval(interval);
    }, []);

    const fetchMembers = async () => {
        try {
            const res = await axios.get("http://localhost:8080/members");
            setMembers(res.data.data);
        } catch (err) {
            console.error("Error fetching members", err);
        }
    };

    const fetchGames = async () => {
        try {
            const res = await axios.get("http://localhost:8080/games");
            setGames(res.data.data);
        } catch (err) {
            console.error("Error fetching games", err);
        }
    };

    const fetchSessions = async () => {
        try {
            const res = await axios.get("http://localhost:8080/sessions");
            setSessions(res.data.data);
        } catch (err) {
            console.error("Error fetching sessions", err);
        }
    };

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleStart = async (e) => {
        e.preventDefault();
        if (!form.memberId || !form.gameId) {
            setMessage("❌ Please select member and game");
            return;
        }
        try {
            const res = await axios.post("http://localhost:8080/sessions/start", {
                memberId: form.memberId,
                gameId: form.gameId,
            });
            setMessage(`✅ ${res.data.message}`);
            setForm({ memberId: "", gameId: "" });
            fetchSessions();
            fetchMembers();
        } catch (err) {
            setMessage("❌ Error starting session");
        }
    };

    const handleEnd = async (sessionId) => {
        try {
            const res = await axios.put(`http://localhost:8080/sessions/end/${sessionId}`);
            setMessage(`✅ ${res.data.message}`);
            fetchSessions();
            fetchMembers();
        } catch (err) {
            setMessage("❌ Error ending session");
        }
    };

    // Check if any session is ongoing
    const hasOngoing = sessions.some((s) => !s.endedAt);

    return (
        <div className="container min-vh-100 mt-5">
            <h3 className="text-success mb-4">🎮 Manage Game Sessions</h3>
            {message && <div className="alert alert-info">{message}</div>}

            {/* Start Session Form */}
            <form className="mb-4" onSubmit={handleStart}>
                <div className="row g-3 align-items-end">
                    <div className="col-md-5">
                        <label className="form-label">Member</label>
                        <select
                            name="memberId"
                            className="form-select"
                            value={form.memberId}
                            onChange={handleChange}
                            required
                        >
                            <option value="">Select Member</option>
                            {members.map((m) => (
                                <option key={m.id} value={m.id}>
                                    {m.name} (Balance: {m.balance.toFixed(2)})
                                </option>
                            ))}
                        </select>
                    </div>

                    <div className="col-md-5">
                        <label className="form-label">Game</label>
                        <select
                            name="gameId"
                            className="form-select"
                            value={form.gameId}
                            onChange={handleChange}
                            required
                        >
                            <option value="">Select Game</option>
                            {games.map((g) => (
                                <option key={g.id} value={g.id}>
                                    {g.name} (Cost: {g.costPerMinute}/min)
                                </option>
                            ))}
                        </select>
                    </div>

                    <div className="col-md-2">
                        <button className="btn btn-success w-100">Start</button>
                    </div>
                </div>
            </form>

            {/* Sessions Table */}
            <h5 className="text-success mb-3">All Game Sessions</h5>
            <table className="table table-dark table-striped">
                <thead>
                    <tr>
                        <th>Member</th>
                        <th>Game</th>
                        <th>Started At</th>
                        <th>Ended At</th>
                        <th>Cost</th>
                        {hasOngoing && <th>Actions</th>}
                    </tr>
                </thead>
                <tbody>
                    {sessions.length === 0 && (
                        <tr>
                            <td colSpan={hasOngoing ? 6 : 5} className="text-center">
                                No sessions found
                            </td>
                        </tr>
                    )}
                    {sessions.map((s) => (
                        <tr key={s.id}>
                            <td>{s.memberName}</td>
                            <td>{s.gameName}</td>
                            <td>{new Date(s.startedAt).toLocaleString()}</td>
                            <td>{s.endedAt ? new Date(s.endedAt).toLocaleString() : "Ongoing"}</td>
                            <td>{s.cost ? s.cost.toFixed(2) : "-"}</td>
                            {hasOngoing && (
                                <td>
                                    {!s.endedAt && (
                                        <button
                                            type="button"
                                            className="btn btn-sm btn-outline-success"
                                            onClick={() => handleEnd(s.id)}
                                        >
                                            End
                                        </button>
                                    )}
                                </td>
                            )}
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default GameSession;