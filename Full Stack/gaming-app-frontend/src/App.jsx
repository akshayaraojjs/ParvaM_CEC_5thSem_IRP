import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import Signup from "./components/Signup";
import Login from "./components/Login";
import Dashboard from "./components/Dashboard";
import Profile from "./components/Profile";
import GamesList from "./components/GamesList";
import GameForm from "./components/GameForm";
import RechargeForm from "./components/RechargeForm";
import RechargeList from "./components/RechargeList";
import GameSession from "./components/GameSession";

function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={
          <div className="text-center text-light bg-dark vh-100 d-flex align-items-center justify-content-center">
            <h1>Welcome to Gaming Club 🎮</h1>
          </div>
        } />
        <Route path="/signup" element={<Signup />} />
        <Route path="/login" element={<Login />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/games" element={<GamesList />} />
        <Route path="/games/create" element={<GameForm />} />
        <Route path="/games/edit/:id" element={<GameForm />} />
        <Route path="/recharge" element={<RechargeForm />} />
        <Route path="/recharge-history" element={<RechargeList />} />
        <Route path="/sessions" element={<GameSession />} />
      </Routes>
    </Router>
  );
}

export default App;