import mouse
import keyboard
import time

mouse.move(1330, 400)

mouse.click("left")
mouse.move(1460, 400)

# Используйте цикл с явным указанием количества повторений
for i in range(1353-503):
    try:
        keyboard.press_and_release("ctrl+s")
        time.sleep(1.7)

        keyboard.press_and_release("enter")
        time.sleep(1)

        mouse.click("middle")
        time.sleep(1)

        keyboard.press_and_release("ctrl+tab")
    except Exception as e:
        print(f"An error occurred: {e}")
