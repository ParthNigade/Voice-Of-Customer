import React, { useEffect, useState } from 'react';
import { Bar, Pie } from 'react-chartjs-2';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  ArcElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';

ChartJS.register(CategoryScale, LinearScale, BarElement, ArcElement, Title, Tooltip, Legend);

const BarChartAndPieChart = () => {
  const [barChartData, setBarChartData] = useState({
    labels: ['Positive Feedback', 'Negative Feedback'],
    datasets: [
      {
        label: 'Customer Sentiment',
        data: [0, 0], // Initial data
        backgroundColor: [
          'rgba(54, 162, 235, 0.8)', // Blue for Positive
          'rgba(255, 99, 132, 0.8)', // Red for Negative
        ],
        borderColor: [
          'rgba(54, 162, 235, 1)', // Blue border for Positive
          'rgba(255, 99, 132, 1)', // Red border for Negative
        ],
        borderWidth: 1,
      },
    ],
  });

  const [pieChartData, setPieChartData] = useState({
    labels: ['Technical', 'HR', 'Customer Service', 'Miscellaneous'],
    datasets: [
      {
        data: [0, 0, 0, 0], // Initial data
        backgroundColor: [
          'rgba(54, 162, 235, 0.8)', // Blue for Technical
          'rgba(255, 99, 132, 0.8)', // Red for HR
          'rgba(255, 206, 86, 0.8)', // Yellow for Customer Service
          'rgba(75, 192, 192, 0.8)'  // Green for Miscellaneous
        ],
        borderColor: [
          'rgba(54, 162, 235, 1)',
          'rgba(255, 99, 132, 1)',
          'rgba(255, 206, 86, 1)',
          'rgba(75, 192, 192, 1)'
        ],
        borderWidth: 1,
      },
    ],
  });

  const [positiveCount, setPositiveCount] = useState(0);
  const [negativeCount, setNegativeCount] = useState(0);
  const [departmentCounts, setDepartmentCounts] = useState([0, 0, 0, 0]);

  const totalQueries = departmentCounts.reduce((acc, count) => acc + count, 0);

  // Fetch data for Bar Chart
  useEffect(() => {
    const fetchBarChartData = async () => {
      try {
        const response = await fetch('https://6a97e303-6c9f-4dbc-8fd9-caf7e8d8e50c.e1-us-east-azure.choreoapps.dev/statistics/sentiment');
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        const data = await response.json();

        const positive = data.positiveCount || 0;
        const negative = data.negativeCount || 0;

        setPositiveCount(positive);
        setNegativeCount(negative);

        setBarChartData({
          labels: ['Positive Feedback', 'Negative Feedback'],
          datasets: [
            {
              label: 'Customer Sentiment',
              data: [positive, negative],
              backgroundColor: [
                'rgba(54, 162, 235, 0.8)', // Blue for Positive
                'rgba(255, 99, 132, 0.8)', // Red for Negative
              ],
              borderColor: [
                'rgba(54, 162, 235, 1)', // Blue border for Positive
                'rgba(255, 99, 132, 1)', // Red border for Negative
              ],
              borderWidth: 1,
            },
          ],
        });
      } catch (error) {
        console.error('Error fetching sentiment data:', error);
      }
    };

    fetchBarChartData();
  }, []);

  // Fetch data for Pie Chart
  useEffect(() => {
    const fetchPieChartData = async () => {
      try {
        const response = await fetch('https://6a97e303-6c9f-4dbc-8fd9-caf7e8d8e50c.e1-us-east-azure.choreoapps.dev/statistics/departmentcount');
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        const data = await response.json();

        const departmentData = [
          data.technicalDepartmentCount || 0,
          data.hrDepartmentCount || 0,
          data.customerServiceDepartmentCount || 0,
          data.miscellaneousDepartmentCount || 0,
        ];

        setDepartmentCounts(departmentData);

        setPieChartData({
          labels: ['Technical', 'HR', 'Customer Service', 'Miscellaneous'],
          datasets: [
            {
              data: departmentData,
              backgroundColor: [
                'rgba(54, 162, 235, 0.8)', // Blue for Technical
                'rgba(255, 99, 132, 0.8)', // Red for HR
                'rgba(255, 206, 86, 0.8)', // Yellow for Customer Service
                'rgba(75, 192, 192, 0.8)'  // Green for Miscellaneous
              ],
              borderColor: [
                'rgba(54, 162, 235, 1)',
                'rgba(255, 99, 132, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)'
              ],
              borderWidth: 1,
            },
          ],
        });
      } catch (error) {
        console.error('Error fetching department count data:', error);
      }
    };

    fetchPieChartData();
  }, []);

  return (
    <div style={{ maxWidth: '1200px', margin: '0 auto', padding: '20px', backgroundColor: '#333', borderRadius: '8px' }}>
      <h1 style={{ textAlign: 'center', color: '#fff' }}>Statistics</h1>
      <h3 style={{ textAlign: 'center', color: '#fff', fontWeight: 'bold' }}>Total Number Of Queries: {totalQueries}</h3>
      <div style={{ display: 'flex', justifyContent: 'space-around', gap: '20px' }}>
        <div style={{ width: '45%' }}>
          <h3 style={{ textAlign: 'center', color: '#fff' }}>Customer Sentiment</h3>
          <Bar
            data={barChartData}
            options={{
              responsive: true,
              scales: {
                y: {
                  beginAtZero: true,
                  grid: {
                    color: 'rgba(255, 255, 255, 0.2)', // Lighter grid lines for better visibility
                  },
                  ticks: {
                    color: '#fff', // White color for y-axis labels
                  },
                },
                x: {
                  grid: {
                    color: 'rgba(255, 255, 255, 0.2)', // Lighter grid lines for better visibility
                  },
                  ticks: {
                    color: '#fff', // White color for x-axis labels
                  },
                },
              },
              plugins: {
                legend: {
                  position: 'top',
                  labels: {
                    color: '#fff', // White color for legend labels
                  },
                },
              },
            }}
          />
          <h4 style={{ textAlign: 'center', color: '#fff' }}>
            Positive Feedback: {positiveCount} | Negative Feedback: {negativeCount}
          </h4>
        </div>
        <div style={{ width: '45%' }}>
          <h3 style={{ textAlign: 'center', color: '#fff' }}>Department Queries Count</h3>
          <Pie
            data={pieChartData}
            options={{
              responsive: true,
              plugins: {
                legend: {
                  position: 'top',
                  labels: {
                    color: '#fff', // White color for legend labels
                  },
                },
              },
            }}
          />
          <h4 style={{ textAlign: 'center', color: '#fff' }}>
            Technical: {departmentCounts[0]} | HR: {departmentCounts[1]} | Customer Service: {departmentCounts[2]} | Miscellaneous: {departmentCounts[3]}
          </h4>
        </div>
      </div>
    </div>
  );
};

export default BarChartAndPieChart;
