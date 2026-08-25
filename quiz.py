import tkinter as tk
from tkinter import messagebox, ttk
from ttkbootstrap import Style
from quiz_data import quiz_data

# Function to display the current question and choices.
def show_question():
    question = quiz_data[current_question]  # Get the current question
    qs_label.config(text=question["question"])  # Display the question

    # Display the choices on the buttons
    choices = question["choices"]
    for i in range(4):
        choice_btns[i].config(text=choices[i], state="normal")  # Enable button

    feedback_label.config(text="")  # Clear feedback
    next_btn.config(state="disabled")  # Disable next button

# Function to check the selected answer and provide feedback
def check_answer(choice=None):
    question = quiz_data[current_question]  # Get the current question

    # Check if the answer is submitted via the Entry widget or the buttons
    selected_choice = entry.get() if choice is None else choice_btns[choice].cget("text")

    # Check if the selected choice matches the correct answer
    if selected_choice == question["answer"]:
        global score
        score += 1  # Update the score
        score_label.config(text=f"Score: {score}/{len(quiz_data)}")  # Display the score
        feedback_label.config(text="Correct!", foreground="green")  # Provide positive feedback
    else:
        feedback_label.config(text="Incorrect!", foreground="red")  # Provide negative feedback

    # Disable all choice buttons and enable the next button
    for button in choice_btns:
        button.config(state="disabled")
    next_btn.config(state="normal")

    entry.delete(0, 'end')  # Clear the entry field

# Function to move to the next question
def next_question():
    global current_question
    current_question += 1  # Move to the next question

    if current_question < len(quiz_data):
        show_question()  # Show the next question
    else:
        # End the quiz and show the final score
        messagebox.showinfo("Quiz Completed", f"Quiz Completed! Final score: {score}/{len(quiz_data)}")
        root.destroy()

# Create the main window
root = tk.Tk()
root.title("Quiz App")
root.geometry("600x500")

# Create a Style object
style = Style(theme="flatly")

# Create a tk.Frame widget
frame = tk.Frame(root, background="lightgrey")
frame.pack(pady=10)

# Create an Entry widget
entry = ttk.Entry(frame)
entry.pack(side="left")
entry.bind('<Return>', lambda event: check_answer())  # Bind the Enter key to the check_answer function

# Create the next button
next_btn = ttk.Button(frame, text="Next", command=next_question, state="disabled")
next_btn.pack(side="left")

# Configure the font size for the question and choice buttons
style.configure("TLabel", font=("Helvetica", 20))
style.configure("TButton", font=("Helvetica", 16))

# Create the question label
qs_label = ttk.Label(root, anchor="center", wraplength=500, padding=10)
qs_label.pack(pady=10)

# Create the choice buttons
choice_btns = []
for i in range(4):
    button = ttk.Button(root, command=lambda i=i: check_answer(i))
    button.pack(pady=5)
    choice_btns.append(button)

# Create the feedback label
feedback_label = ttk.Label(root, anchor="center", padding=10)
feedback_label.pack(pady=10)

# Initialize the score
score = 0

# Create the score label
score_label = ttk.Label(root, text=f"Score: 0/{len(quiz_data)}", anchor="center", padding=10)
score_label.pack(pady=10)

# Initialize the current question index
current_question = 0

# Show the first question
show_question()

# Start the main event loop
root.mainloop()
