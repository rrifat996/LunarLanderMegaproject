from py4j.java_gateway import JavaGateway
from tensorflow.keras.layers import Dense, Input
from tensorflow.keras.optimizers import Adam

def print_hi(name):
    # Use a breakpoint in the code line below to debug your script..
    print(f'Hi, {name}')  # Press Ctrl+F8 to toggle the breakpoint.

# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print_hi('PyCharm')
    gateway = JavaGateway()
    main = gateway.entry_point
    print(main.testMethod())
    print(main.getNumber())

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
