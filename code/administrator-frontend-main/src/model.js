import React from 'react';
import './css/model.css';

const Modal = ({ query, onClose, onResolve }) => {
    if (!query) return null;

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <span className="close-button" onClick={onClose}>×</span>
                <div className="modal-body">
                    <h3>Query Details</h3>
                    <p><strong>ID:</strong> {query.id}</p>
                    <p><strong>Date:</strong> {query.createdAt.split('T')[0]}</p>
                    <h4><strong>Query:</strong> {query.query}</h4>
                    <p><strong>Suggestion:</strong> {query.querySolution}</p>
                    <button onClick={() => onResolve(query.id)} className="resolve-button">
                        Mark as Resolved
                    </button>
                </div>
            </div>
        </div>
    );
};

export default Modal;
