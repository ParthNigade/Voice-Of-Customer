import React, { useEffect, useState } from 'react';
import Modal from './model';

const CustomerService = () => {
    const [queries, setQueries] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [selectedQuery, setSelectedQuery] = useState(null);
    const [selectedPriority, setSelectedPriority] = useState('');

    useEffect(() => {
        fetch('https://6a97e303-6c9f-4dbc-8fd9-caf7e8d8e50c.e1-us-east-azure.choreoapps.dev/Customerservice')
            .then(response => response.json())
            .then(data => {
                setQueries(data);
                setLoading(false);
            })
            .catch(error => {
                console.error('Error fetching queries:', error);
                setError('Failed to load queries. Please try again later.');
                setLoading(false);
            });
    }, []);

    const handlePriorityChange = (e) => {
        const { value, checked } = e.target;
        setSelectedPriority(checked ? value : '');
    };

    const getPriorityColor = (priority) => {
        switch (priority) {
            case 'High':
                return 'red';
            case 'Medium':
                return 'orange';
            case 'Low':
                return 'gray';
            case 'None':
                return 'silver';
            default:
                return 'gray';
        }
    };

    // Update filtering logic to show all queries if no priority is selected
    const filteredQueries = queries.filter(query => {
        if (!selectedPriority) {
            // Show all queries if no priority is selected
            return true;
        }
        return query.priority === selectedPriority;
    });

    const handleQueryClick = (query) => {
        setSelectedQuery(query);
    };

    const handleResolveQuery = (id) => {
        setQueries(prevQueries => prevQueries.filter(query => query.id !== id));
        setSelectedQuery(null);
    };

    const handleCloseModal = () => {
        setSelectedQuery(null);
    };

    return (
        <div className="department-page">
            {loading ? (
                <p>Loading queries...</p>
            ) : error ? (
                <p className="error-message">{error}</p>
            ) : (
                <>
                    <h2>Customer Service Queries</h2>

                    {/* Centered Priority Checkboxes */}
                    <div style={{ display: 'flex', justifyContent: 'center', marginBottom: '20px' }}>
                        <label style={{ display: 'flex', alignItems: 'center', margin: '0 10px' }}>
                            <input
                                type="checkbox"
                                value="High"
                                checked={selectedPriority === 'High'}
                                onChange={handlePriorityChange}
                                style={{
                                    marginRight: '10px',
                                    cursor: 'pointer',
                                    width: '20px',
                                    height: '20px',
                                }}
                            />
                            High Priority
                        </label>

                        <label style={{ display: 'flex', alignItems: 'center', margin: '0 10px' }}>
                            <input
                                type="checkbox"
                                value="Medium"
                                checked={selectedPriority === 'Medium'}
                                onChange={handlePriorityChange}
                                style={{
                                    marginRight: '10px',
                                    cursor: 'pointer',
                                    width: '20px',
                                    height: '20px',
                                }}
                            />
                            Medium Priority
                        </label>

                        <label style={{ display: 'flex', alignItems: 'center', margin: '0 10px' }}>
                            <input
                                type="checkbox"
                                value="Low"
                                checked={selectedPriority === 'Low'}
                                onChange={handlePriorityChange}
                                style={{
                                    marginRight: '10px',
                                    cursor: 'pointer',
                                    width: '20px',
                                    height: '20px',
                                }}
                            />
                            Low Priority
                        </label>
                    </div>

                    <div className="query-container">
                        {filteredQueries.map((query, index) => (
                            <div 
                                key={index} 
                                className="query-box" 
                                onClick={() => handleQueryClick(query)}
                                style={{ cursor: 'pointer' }}
                            >
                                <div className="query-header">
                                    <p><strong>ID:</strong> {query.id}</p>
                                    {query.priority && (
                                        <span 
                                            className="priority-bubble" 
                                            style={{ backgroundColor: getPriorityColor(query.priority) }}
                                        >
                                            {query.priority}
                                        </span>
                                    )}
                                </div>
                                <p><strong>Date:</strong> {query.createdAt.split('T')[0]}</p>
                                <h4><strong>Query:</strong> {query.query}</h4>
                            </div>
                        ))}
                    </div>

                    {selectedQuery && (
                        <Modal
                            query={selectedQuery}
                            onClose={handleCloseModal}
                            onResolve={handleResolveQuery}
                        />
                    )}
                </>
            )}
        </div>
    );
};

export default CustomerService;
