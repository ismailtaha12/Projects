function generateDietPlan() {
    // Define calorie distribution percentages
    const carbMinPercent = 45;
    const carbMaxPercent = 65;
    const proteinMinPercent = 10;
    const proteinMaxPercent = 35;
    const fatMinPercent = 20;
    const fatMaxPercent = 35;

    // Calculate calorie ranges for each macronutrient
    // const totalCalories = 2000;

    // const carbMinCalories = totalCalories * (carbMinPercent / 100);
    // const carbMaxCalories = totalCalories * (carbMaxPercent / 100);

    // const proteinMinCalories = totalCalories * (proteinMinPercent / 100);
    // const proteinMaxCalories = totalCalories * (proteinMaxPercent / 100);

    // const fatMinCalories = totalCalories * (fatMinPercent / 100);
    // const fatMaxCalories = totalCalories * (fatMaxPercent / 100);

    // // Convert calorie ranges to grams
    // const carbMinGrams = carbMinCalories / 4; // 4 calories per gram of carb
    // const carbMaxGrams = carbMaxCalories / 4;

    // const proteinMinGrams = proteinMinCalories / 4; // 4 calories per gram of protein
    // const proteinMaxGrams = proteinMaxCalories / 4;

    // const fatMinGrams = fatMinCalories / 9; // 9 calories per gram of fat
    // const fatMaxGrams = fatMaxCalories / 9;

    // Define meals with calorie and macronutrient content
    const Protien = [
        {
            "Meal": "Type1",
            "Food": "Chicken Breast",
            "Calories": 165,
            "Protein": 31,
            "Carbs": 0,
            "Fats": 3.6,
            "Grams": 100
        },
        {
            "Meal": "Type2",
            "Food": "Steak",
            "Calories": 271,
            "Protein": 25,
            "Carbs": 0,
            "Fats": 19,
            "Grams": 100
        },
        {
            "Meal": "Type3",
            "Food": "Salmon",
            "Calories": 208,
            "Protein": 20,
            "Carbs": 0,
            "Fats": 13,
            "Grams": 100
        },
        {
            "Meal": "Type4",
            "Food": "Eggs",
            "Calories": 155,
            "Protein": 13,
            "Carbs": 1.1,
            "Fats": 11,
            "Grams": 100
        },
        {
            "Meal": "Type5",
            "Food": "Turkey Breast",
            "Calories": 189,
            "Protein": 29,
            "Carbs": 0,
            "Fats": 7,
            "Grams": 100
        }
    ];
    function Protienmain(pro){
        for(const item of Protien){
            let MP=(item.protien)/100;
            item.Calories=(item.Calories)/100;
            item.Fats=(item.Fats)/100;
            item.Grams=(item.Grams)/100;
            item.protein=(item.protien)/100;
            let g=pro/MP;
            item.Calories=(item.Calories)*g;
            item.Fats=(item.Fats)*g;
            item.Grams=(item.Grams)*g;
            item.protein=(item.protien)*g;
            let promeals=[];
            promeals.push(item);
            console.log(item);
        }
    }
    const CarbsMain=[
        {
            "Meal": "Type1",
            "Food": "Steamed Rice",
            "Calories": 130,
            "Protein":2.7,
            "Carbs": 28,
            "Fats": 0.3,
            "Grams": 100
        },
        {
            "Meal": "Type2",
            "Food": "Oat Pasta",
            "Calories": 361,
            "Protein":12,
            "Carbs": 70,
            "Fats": 3,
            "Grams": 100
        },
         {
            "Meal": "Type3",
            "Food": "Potatoes",
            "Calories": 96,
            "Protein":2.5,
            "Carbs": 22,
            "Fats": 0.1,
            "Grams": 100
         }
    ]

    const BreakCarbs=[
        {
            "Meal": "Type1",
            "Food": "Oats with Skimmed Milk",
            "Calories": 450,
            "Protein":20.45,
            "Carbs": 77.70,
            "Fats": 13.92,
            "Grams of oats": 100,
            "Millis of Milk": 200
        },
        {
            "Meal": "Type2",
            "Food": "Brown Toast",
            "Calories": 267,
            "Protein":11,
            "Carbs": 49,
            "Fats": 3,
            "Grams": 100
        },
        {
            "Meal": "Type3",
            "Food": "Corn Flakes",
            "Calories": 357,
            "Protein":8,
            "Carbs": 84,
            "Fats": 0.4,
            "Grams": 100
        }
    ]

    // Populate table with meals
    const mealTableBody = document.getElementById("mealTableBody");
    meals.forEach(meal => {
        const row = mealTableBody.insertRow();

        row.innerHTML = `
            <td>${meal.Meal}</td>
            <td>${meal.Food}</td>
            <td>${meal.Calories}</td>
            <td>${meal.Protein}</td>
            <td>${meal.Carbs}</td>
            <td>${meal.Fats}</td>
            <td>${meal.TotalCalories}</td>
        `;
    });
}

const BTNACTION=document.getElementById("GB");
const SelectedElement=document.getElementById("IN");
var CarbDays=[7];
var protien;
var calpro;

function totalcarbs(){
    if(SelectedElement==="Weight Loss"){
        cal=document.getElementById("INBMI")-500;
        carb=((50*cal)/100)/4;
    }else if(SelectedElement==="Weight Gain"){
        cal=document.getElementById("INBMI")+900;
        carb=((50*cal)/100)/4;
    }else if(SelectedElement==="Lean Bulking"){
        cal=document.getElementById("INBMI")+500;
        carb=((50*cal)/100)/4;
    }
    return carb;
}
function totalcarbcutting(){
    for(var i=0;i<7;i++){
        if(i<4){
            cal=document.getElementById("INBMI")-500;
            CarbDays[i]=((50*cal)/100)/4;
        }else if(4<=i>6){
            cal=document.getElementById("INBMI")-650;
            CarbDays[i]=((50*cal)/100)/4;
        }else if(6<=i>=7){
            cal=document.getElementById("INBMI")-750;
            CarbDays[i]=((50*cal)/100)/4;
        }
    }
}
function constprotein(){
    calpro=document.getElementById("INBMI");
    protien=((35*calpro)/100)/4;
}

function calculate(){
    const carbMinPercent = 45;
    const carbMaxPercent = 65;
    const proteinMinPercent = 10;
    const proteinMaxPercent = 35;
    const fatMinPercent = 20;
    const fatMaxPercent = 35;
    var cal;
    var carb;
    var pro;
    var fat;
    const avrcarb=(carbMinPercent+carbMaxPercent)/2;

    if(SelectedElement==="Weight Loss"){
        cal=document.getElementById("INBMI")-500;
        carb=(cal*((carbMinPercent+carbMaxPercent)/2))/100;
    }




}

GB.addEventListener("click", function() {
    console.log("Button clicked"); // Log to check if button click is detected

    extraDiv.innerHTML = ""; // Clear previous content

    // Check the selected target
    const target = SelectedElement.value;
    console.log("Selected target:", target);

    // Calculate macros based on the selected target
    let carbPercent, proteinPercent;
    if (target === "Weight Loss") {
        carbPercent = 50; // Example percentage for weight loss
        proteinPercent = 35;
    } else if (target === "Weight Gain") {
        carbPercent = 50; // Example percentage for weight gain
        proteinPercent = 35;
    } else if (target === "Lean Bulking") {
        carbPercent = 50; // Example percentage for lean bulking
        proteinPercent = 35;   
    } else if (target === "cutting") {
        carbPercent = 50; // Example percentage for cutting
        proteinPercent = 35;
    } else {
        alert("Please select a target.");
        return; // Stop execution if no target is selected
    }
});










// const BTNACTION = document.getElementById("GB");
// const SelectedElement = document.getElementById("IN");
// const extraDiv = document.querySelector(".extra");

// BTNACTION.addEventListener("click", function() {
//     extraDiv.innerHTML = ""; // Clear previous content

//     // Check the selected target
//     const target = SelectedElement.value;

//     // Calculate macros based on the selected target
//     let carbPercent, proteinPercent;
//     if (target === "Weight Loss") {
//         carbPercent = 50; // Example percentage for weight loss
//         proteinPercent = 35;
//         console.log(target);
//     } else if (target === "Weight Gain") {
//         carbPercent = 50; // Example percentage for weight gain
//         proteinPercent = 35;
//         console.log(target);
//     } else if (target === "Lean Bulking") {
//         carbPercent = 50; // Example percentage for lean bulking
//         proteinPercent = 35;   
//         console.log(target);
//     } else if (target === "cutting") {
//         carbPercent = 50; // Example percentage for cutting
//         proteinPercent = 35;
//         console.log(target);
//     } else {
//         alert("Please select a target.");
//         return; // Stop execution if no target is selected
//     }

//     // Calculate total grams of macros based on BMI
//     const BMI = parseFloat(document.getElementById("INBMI").value);
//     const totalCalories = BMI * 10; // Example calculation for total calories
//     const totalCarbs = (carbPercent / 100) * totalCalories / 4; // 4 calories per gram of carb
//     const totalProtein = (proteinPercent / 100) * totalCalories / 4; // 4 calories per gram of protein

//     // Create and append the table to the extraDiv
//     const table = document.createElement("table");
//     table.innerHTML = `
//         <thead>
//             <tr>
//                 <th>Macro</th>
//                 <th>Grams</th>
//             </tr>
//         </thead>
//         <tbody>
//             <tr>
//                 <td>Carbs</td>
//                 <td>${totalCarbs.toFixed(2)}</td>
//             </tr>
//             <tr>
//                 <td>Protein</td>
//                 <td>${totalProtein.toFixed(2)}</td>
//             </tr>
//             <!-- Add more rows for other macros as needed -->
//         </tbody>
//     `;
//     extraDiv.appendChild(table);
// });


















































// Call the function to generate the diet plan









// const intro = document.getElementById("intro");
//     intro.innerHTML = ""; // Clear the content

//     const text = intro.textContent.trim();

//     // Function to type text character by character
//     function typeWriter(text, index) {
//         if (index < text.length) {
//             intro.innerHTML += text.charAt(index);
//             // Check if the width of the container is exceeded
//             // if (intro.scrollWidth > intro.offsetWidth) {
//             //     intro.innerHTML += "<br>"; // Insert a line break
//             // }
//             setTimeout(function() {
//                 typeWriter(text, index + 1);
//             }, 50); // Delay before typing the next character (adjust as needed)
//         }
//     }

//     // Start typing animation
//     typeWriter(text, 0);
// const intro = document.getElementById("intro");
// intro.innerHTML = ""; // Clear the content

// const text = "NUTRIFIT aims to revolutionize the way individuals approach their fitness goals by providing tailored nutrition and workout plans. Through the integration of advanced AI technology, we offer personalized plans designed to assist users in achieving their specific objectives, whether it's bulking, cutting, weight loss, or weight gain. Our platform emphasizes customization, expert guidance, and adaptability to ensure optimal results for every user.";

// // Function to type text character by character
// function typeWriter(text, index) {
//     if (index < text.length) {
//         intro.innerHTML += text.charAt(index);
//         // Check if the width of the container is exceeded
//         if (intro.scrollWidth > intro.offsetWidth) {
//             intro.innerHTML += "<br>"; // Insert a line break
//         }
//         setTimeout(function() {
//             typeWriter(text, index + 1);
//         }, 50); // Delay before typing the next character (adjust as needed)
//     }
// }

// // Start typing animation
// typeWriter(text, 0);

// const intro = document.getElementById("intro");
// intro.innerHTML = ""; // Clear the content

// const text = "NUTRIFIT aims to revolutionize the way individuals approach their fitness goals by providing tailored nutrition and workout plans. Through the integration of advanced AI technology, we offer personalized plans designed to assist users in achieving their specific objectives, whether it's bulking, cutting, weight loss, or weight gain. Our platform emphasizes customization, expert guidance, and adaptability to ensure optimal results for every user.";

// // Function to write text line by line
// function typeWriter(text, lines, index) {
//     if (index < lines.length) {
//         const line = lines[index];
//         intro.innerHTML += line + "<br>"; // Append the line with a line break
//         setTimeout(function() {
//             typeWriter(text, lines, index + 1);
//         }, 1000); // Delay before typing the next line (adjust as needed)
//     }
// }

// // Split the text into lines
// const lines = text.split("\n");

// // Start typing animation
// typeWriter(text, lines, 0);

// const intro = document.getElementById("intro");
// intro.innerHTML = ""; // Clear the content

// const text = "NUTRIFIT aims to revolutionize the way individuals approach their fitness goals by providing tailored nutrition and workout plans. Through the integration of advanced AI technology, we offer personalized plans designed to assist users in achieving their specific objectives, whether it's bulking, cutting, weight loss, or weight gain. Our platform emphasizes customization, expert guidance, and adaptability to ensure optimal results for every user.";

// // Function to write text character by character
// function typeWriter(text, i, cb) {
//     if (i < text.length) {
//         intro.innerHTML += text.charAt(i);
//         setTimeout(function() {
//             typeWriter(text, i + 1, cb);
//         }, 50); // Adjust the speed as needed
//     } else {
//         cb(); // Callback function when typing is done
//     }
// }

// // Start typing animation
// typeWriter(text, 0, function() {
//     // Animation complete
// });
