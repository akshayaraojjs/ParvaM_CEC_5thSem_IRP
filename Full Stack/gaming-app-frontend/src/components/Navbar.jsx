import { Link } from "react-router-dom";
import { useState, useEffect } from "react";
import { FaGamepad, FaUserCircle, FaWallet, FaList, FaPlayCircle } from "react-icons/fa";

function Navbar() {
    const [member, setMember] = useState(null);

    useEffect(() => {
        const savedMember = localStorage.getItem("member");
        if (savedMember) {
            setMember(JSON.parse(savedMember));
        }
    }, []);

    const handleLogout = () => {
        localStorage.removeItem("member");
        window.location.href = "/login";
    };

    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
            <div className="container">
                <Link className="navbar-brand d-flex align-items-center" to="/">
                    <FaGamepad className="me-2 text-success" /> Gaming Club
                </Link>
                <button
                    className="navbar-toggler"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarNav"
                    aria-controls="navbarNav"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
                >
                    <span className="navbar-toggler-icon"></span>
                </button>

                <div className="collapse navbar-collapse" id="navbarNav">
                    {member && (
                        <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                            <li className="nav-item">
                                <Link className="nav-link" to="/dashboard">
                                    <FaUserCircle className="me-1" /> Dashboard
                                </Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/recharge">
                                    <FaWallet className="me-1" /> Recharge
                                </Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/games">
                                    <FaList className="me-1" /> Games
                                </Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/sessions">
                                    <FaPlayCircle className="me-1" /> Game Sessions
                                </Link>
                            </li>
                        </ul>
                    )}
                    <div className="d-flex align-items-center">
                        {member ? (
                            <>
                                <FaUserCircle className="text-success me-2 fs-4" />
                                <Link to="/profile" className="text-light me-3 text-decoration-none">{member.name}</Link>
                                <button onClick={handleLogout} className="btn btn-outline-success">Logout</button>
                            </>
                        ) : (
                            <>
                                <Link to="/login" className="btn btn-outline-success me-2">Login</Link>
                                <Link to="/signup" className="btn btn-success">Signup</Link>
                            </>
                        )}
                    </div>
                </div>
            </div>
        </nav>
    );
}

export default Navbar;