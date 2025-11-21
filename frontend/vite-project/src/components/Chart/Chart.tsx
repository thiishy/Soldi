import { Chart as ChartJS } from "chart.js/auto";//necessario para funcionar no web
import {Bar,Doughnut} from "react-chartjs-2";
import sourceData from "../../data/sourceData.json";
import revenueData from "../../data/revenueData.json";
import "./Chart.css";

export const Chart = () => {
    return (
        <div className="App">
            {/* <div className="dataCard revenueCard"> 
                <Line data={{
                    labels: sourceData.map((data) => data.label),
                    datasets:[
                        {
                            label: "Revenue",
                            data: sourceData.map((data) => data.value),
                            backgroundColor: "rgba(75,192,192,1)",
                            borderColor: "rgba(75,192,192,1)",
                        },
                        {
                            label: "Cost",
                            data: sourceData.map((data) => data.value),
                            backgroundColor: "rgba(192, 122, 75, 1)",
                            borderColor: "rgba(192, 132, 75, 1)",
                        },
                    ],
                }} 
                />
            </div> */}
            <div className="dataCard bar customerCard">
                <Bar 
                    data={{
                        labels:revenueData.map((data) => data.label),
                        datasets: [
                            {
                                label: "Count",
                                data: sourceData.map((data) => data.value),
                                backgroundColor: [
                                    "rgba(53, 162, 235, 0.5)",
                                    "rgba(59, 235, 53, 0.5)",
                                    "rgba(235, 208, 53, 0.5)",   
                                ],
                                borderRadius: 5,
                            },
                        ],
                    }}
                />
            </div>
            
            <div className="dataCard donut customerCard">
                <Doughnut
                    data={{
                        labels:sourceData.map((data) => data.label),
                        datasets:[
                            {
                                label: "Count",
                                data: sourceData.map((data) => data.value),
                                backgroundColor: [
                                    "rgba(59, 235, 53, 0.5)",
                                    "rgba(235, 156, 53, 0.5)",
                                    "rgba(235, 53, 190, 0.5)",
                                ],
                                borderColor: [
                                    "rgba(59, 235, 53, 0.5)",
                                    "rgba(235, 156, 53, 0.5)",
                                    "rgba(235, 53, 190, 0.5)",
                                ]
                            },
                        ]
                    }}
                
                />

            </div>
        </div>
    )
}