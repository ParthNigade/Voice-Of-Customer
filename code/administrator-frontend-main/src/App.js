import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import Technical from './Technical';
import CustomerService from './Customerservice';
import HR from './Hr';
import Miscellaneous from './Miscellaneous';
import Home from "./Home";
import './css/style.css';

function App() {
    return (
        <Router>
            <div className="App">
                <header className="App-header">
                    <h1>Administrator Dashboard</h1>
                    <nav>
                        <ul>
                            <li><Link to="/technical">Technical</Link></li>
                            <li><Link to="/customerservice">Customer Service</Link></li>
                            <li><Link to="/hr">HR</Link></li>
                            <li><Link to="/miscellaneous">Miscellaneous</Link></li>

                        </ul>
                        <ul>
                        <li><Link to="/">Stats</Link></li>
                        </ul>
                    </nav>
                </header>

                <main className="App-main">
                    <Routes>
                        <Route path="/" element={<Home />} />
                        <Route path="/technical" element={<Technical />} />
                        <Route path="/customerservice" element={<CustomerService />} />
                        <Route path="/hr" element={<HR />} />
                        <Route path="/miscellaneous" element={<Miscellaneous />} />
                    </Routes>
                </main>

        
            </div>
        </Router>
    );
}

export default App;
