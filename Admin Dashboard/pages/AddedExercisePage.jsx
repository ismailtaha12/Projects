import React from 'react'
import ExerciseListing from '../components/ExerciseListing'

const AddedExercisePage = ({ addedExercise }) => {
    return (
        <div>
            <h1>Added Exercises</h1>
            <div>
                {addedExercises.map(exercise => (
                    <ExerciseListing key={exercise.id} exercise={exercise} />
                ))}
            </div>

        </div>
    )
}

export default AddedExercisePage