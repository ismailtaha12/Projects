document.addEventListener("DOMContentLoaded", function () {
    const cardNumberInput = document.getElementById('cardNumber');
    const holderNameInput = document.getElementById('holderName');
    const expiryInput = document.getElementById('expiry');
    const cvvInput = document.getElementById('cvv');
    const emailInput = document.getElementById('email'); // Add email input

    // Function to validate card number
    cardNumberInput.addEventListener('input', function () {
        const cardNumber = this.value.trim();
        if (cardNumber.length !== 16 || isNaN(cardNumber)) {
            this.setCustomValidity('Card number should contain exactly 16 digits');
        } else {
            this.setCustomValidity('');
        }
    });

    // Function to validate card holder name
    holderNameInput.addEventListener('input', function () {
        const holderName = this.value.trim();
        if (!/^[a-zA-Z\s]+$/.test(holderName)) {
            this.setCustomValidity('Card holder name should contain only letters');
        } else {
            this.setCustomValidity('');
        }
    });

    // Function to validate expiry
    expiryInput.addEventListener('input', function () {
        const expiry = this.value.trim();
        if (expiry.length !== 5 || isNaN(expiry.substring(0, 2)) || isNaN(expiry.substring(3, 5)) || expiry.charAt(2) !== '/') {
            this.setCustomValidity('Expiry should be in MM/YY format');
        } else {
            this.setCustomValidity('');
        }
    });

    // Function to validate CVV
    cvvInput.addEventListener('input', function () {
        const cvv = this.value.trim();
        if (cvv.length !== 3 || isNaN(cvv)) {
            this.setCustomValidity('CVV should contain exactly 3 digits');
        } else {
            this.setCustomValidity('');
        }
    });

    // Function to validate email
    emailInput.addEventListener('input', function () {
        const email = this.value.trim();
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; // Regular expression for email validation
        if (!emailRegex.test(email)) {
            this.setCustomValidity('Please enter a valid email address');
        } else {
            this.setCustomValidity('');
        }
    });

    // Function to validate all input fields are not empty
    function validateForm() {
        const inputs = document.querySelectorAll('input[type="text"]');
        let isValid = true;
        inputs.forEach(input => {
            if (input.value.trim() === '') {
                input.classList.add('invalid');
                isValid = false;
            } else {
                input.classList.remove('invalid');
            }
        });
        return isValid;
    }

    // Function to handle form submission
    function handleSubmit(event) {
        if (!validateForm()) {
            alert('Please fill in all the fields.');
            event.preventDefault(); // Prevent form submission if any field is empty
        }
    }

    // Attach event listener to form submission
    const form = document.querySelector('form');
    form.addEventListener('submit', handleSubmit);
});
