#!/usr/bin/python
import sys
import matplotlib.pyplot as plt

parentRadius = 225
circle1 = plt.Circle((0,0), 5)

def create_circle():
    global parentRadius
    ## circlex = plt.Circle((xy), radius, color)
    circle = plt.Circle((0,0), radius = parentRadius, color=(0.2, 0.4, 0.8, 0.5))
    circle.set_edgecolor('none')
    parentRadius = parentRadius/3
    ## random uniform number between 0 and 1

    return circle

def create_cluster():
    global parentRadius
    global circle1
    circle1 = plt.Circle((0,0), radius = parentRadius, color=(0.2, 0.5, 0.1, 0.1))
    circle1.set_edgecolor('none')

# Make a list of circles, and add in for loop using add_artist(i)
def show_shape(patch):
    graph_size = 300
    fig, ax = plt.subplots()
    ax.add_artist(patch)
    ax.add_artist(circle1)
    plt.axis('equal')
    plt.axis([-graph_size, graph_size, -graph_size, graph_size])
    plt.axis('off')
    plt.show()

if __name__=='__main__':
    generations = 2

    plt.close()

    if(generations > 1):
        c = create_circle()
    if(generations >= 2):
        create_cluster()


    show_shape(c)

