#!/usr/bin/python
import sys
import random
import shlex
import matplotlib.pyplot as plt
from fractions import Fraction
from argparse import ArgumentParser

generations = 3
circles = []
colorList = []
ratio = []
inputColor = []

def create_parent(parentRadius):
    global colorList, circles
    circle = plt.Circle((0, 0), radius = parentRadius, color=colorList[0])
    circles.append(circle)

def create_colors():
    global colorList, generations

    i = 0
    while i < generations:
        colorList.append([random.uniform(0, 1.0), random.uniform(0, 1.0), random.uniform(0, 1.0)])
        i = i + 1

def create_cluster(subX, subY, parentRadius, printedGenerations):
    global colorList, clusterFlag, circles

    if printedGenerations == generations:   
        return None
        
    # Center
    circleC = plt.Circle((subX,subY), radius = parentRadius, color=colorList[printedGenerations])
    # Left
    circleL = plt.Circle((subX-(2*parentRadius),subY), radius = parentRadius, color=colorList[printedGenerations])
    # Right
    circleR = plt.Circle((subX+(2*parentRadius),subY), radius = parentRadius, color=colorList[printedGenerations])
    # Top Left
    circleTL = plt.Circle((subX-(parentRadius), subY + (int((1.75*parentRadius)))), radius = parentRadius, color=colorList[printedGenerations])
    # Top Right
    circleTR = plt.Circle((subX+(parentRadius), subY + (int((1.75*parentRadius)))), radius = parentRadius, color=colorList[printedGenerations])
    # Bottom Left
    circleBL = plt.Circle((subX-(parentRadius), subY - (int((1.75*parentRadius)))), radius = parentRadius, color=colorList[printedGenerations])
    # Bottom Right
    circleBR = plt.Circle((subX+(parentRadius), subY - (int((1.75*parentRadius)))), radius = parentRadius, color=colorList[printedGenerations])

    circles.append(circleC)
    circles.append(circleL)
    circles.append(circleR)
    circles.append(circleTL)
    circles.append(circleTR)
    circles.append(circleBL)
    circles.append(circleBR)

    if printedGenerations < generations:
        # Left
        create_cluster(subX-(2*parentRadius), subY, parentRadius / 3, printedGenerations + 1)
        # Center
        create_cluster(subX, subY, parentRadius / 3, printedGenerations + 1)

        # Right
        create_cluster(subX+(2*parentRadius), subY, parentRadius / 3, printedGenerations + 1)
        # Top Left
        create_cluster(subX-(parentRadius), subY + (int((1.75*parentRadius))), parentRadius / 3, printedGenerations + 1)
        # Top Right
        create_cluster(subX+(parentRadius), subY + (int((1.75*parentRadius))), parentRadius / 3, printedGenerations + 1)
        # Bottom Left
        create_cluster(subX-(parentRadius), subY - (int((1.75*parentRadius))), parentRadius / 3, printedGenerations + 1)
        # Bottom Right
        create_cluster(subX+(parentRadius), subY - (int((1.75*parentRadius))), parentRadius / 3, printedGenerations + 1)

    return

# Make a list of circles, and add in for loop using add_artist(i)
def show_shape():
    global circles
    graph_size = 300
    fig, ax = plt.subplots()
    i = 0
    for i in circles:
        ax.add_artist(i)

    plt.axis('scaled')
    plt.axis([-graph_size, graph_size, -graph_size, graph_size])
    plt.axis('off')
    plt.show()

if __name__=='__main__':

    plt.close()
    ## if len(argv) == 2

    argument = ArgumentParser()
    argument.add_argument('-file', metavar='FILE', help='input file for Circles. <Ratio> <R> <G> <B>')
    args = argument.parse_args()
    

    if(args.file):
        with open(args.file) as file:
            lines = []
            for line in open(args.file):
                line = shlex.split(line)
                lines.append(line)
                
    # what the fuuuuuck, why isnt this running correctly? Thinks i is an Integer and not an int???
    i = 0
    for i in lines:
        if i == 1:
            ratio = float(Fraction(lines[1][0]))
    print ratio
    #
    create_colors()

    if(generations > 1):
        create_parent(300 * ratio)
    if(generations >= 2):
        create_cluster(0, 0, 300/3, 1)

    show_shape()

