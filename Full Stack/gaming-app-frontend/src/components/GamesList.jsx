import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

function GamesList() {
  const [games, setGames] = useState([]);
  const [message, setMessage] = useState("");

  const fetchGames = async () => {
    try {
      const res = await axios.get("http://localhost:8080/games");
      setGames(res.data.data);
    } catch (err) {
      setMessage("❌ Failed to fetch games");
    }
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Are you sure you want to delete this game?")) return;
    try {
      await axios.delete(`http://localhost:8080/games/${id}`);
      setGames(games.filter((g) => g.id !== id));
      setMessage("✅ Game deleted successfully");
    } catch {
      setMessage("❌ Delete failed");
    }
  };

  useEffect(() => {
    fetchGames();
  }, []);

  return (
    <div className="container min-vh-100 mt-5">
      <div className="d-flex justify-content-between align-items-center mb-4">
        <h2 className="text-success">🎮 Games</h2>
        <Link to="/games/create" className="btn btn-success shadow-sm">
          Add New Game
        </Link>
      </div>

      {message && <div className="alert alert-info">{message}</div>}

      <div className="row">
        {games.map((game) => (
          <div key={game.id} className="col-md-4 mb-4">
            <div className="card h-100 shadow-sm rounded-4 border-0">
              <div className="card-body d-flex flex-column">
                <h5 className="card-title fw-bold">{game.name}</h5>
                <p className="card-text text-muted flex-grow-1">
                  {game.description || "No description available."}
                </p>
                <p className="mb-1"><strong>Category:</strong> {game.category || "N/A"}</p>
                <p className="mb-3"><strong>Cost per min:</strong> ${game.costPerMinute.toFixed(2)}</p>
                <div className="mt-auto d-flex gap-2">
                  <Link
                    to={`/games/edit/${game.id}`}
                    className="btn btn-outline-success w-50"
                  >
                    Edit
                  </Link>
                  <button
                    className="btn btn-outline-danger w-50"
                    onClick={() => handleDelete(game.id)}
                  >
                    Delete
                  </button>
                </div>
              </div>
            </div>
          </div>
        ))}
        {games.length === 0 && (
          <div className="col-12 text-center text-muted">
            No games found.
          </div>
        )}
      </div>
    </div>
  );
}

export default GamesList;