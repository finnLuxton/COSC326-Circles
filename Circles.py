#!/usr/bin/python
import sys
import random
import shlex
import matplotlib.pyplot as plt
from fractions import Fraction
from argparse import ArgumentParser

# COSC326 Etude 13 - Circle
# Author - Finn Luxton 6362897

generations = 3
circles = []
colorList = []
ratio = []
inputColor = []

# Creates the main parent circle which will encompass all other inner circles
def create_parent(parentRadius):
    global colorList, circles
    circle = plt.Circle((0, 0), radius = parentRadius, color=colorList[0])
    circles.append(circle)

# Creates an array of random colors if no color input is given
def create_colors():
    global colorList, generations
    i = 0
    while i < generations:
        colorList.append([random.uniform(0, 1.0), random.uniform(0, 1.0), random.uniform(0, 1.0)])
        i = i + 1

# Creates a cluster of circles inside a parent circle
def create_cluster(subX, subY, parentRadius, printedGenerations):
    global colorList, circles

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

    circles.extend([circleC, circleL, circleR, circleTL, circleTR, circleBL, circleBR])

    if printedGenerations < generations:
        # Left
        create_cluster(subX-(2*parentRadius), subY, parentRadius * 0.333, printedGenerations + 1)
        # Center
        create_cluster(subX, subY, parentRadius * 0.333, printedGenerations + 1)
        # Right
        create_cluster(subX+(2*parentRadius), subY, parentRadius * 0.333, printedGenerations + 1)
        # Top Left
        create_cluster(subX-(parentRadius), subY + (int((1.75*parentRadius))), parentRadius * 0.333, printedGenerations + 1)
        # Top Right
        create_cluster(subX+(parentRadius), subY + (int((1.75*parentRadius))), parentRadius * 0.333, printedGenerations + 1)
        # Bottom Left
        create_cluster(subX-(parentRadius), subY - (int((1.75*parentRadius))), parentRadius * 0.333, printedGenerations + 1)
        # Bottom Right
        create_cluster(subX+(parentRadius), subY - (int((1.75*parentRadius))), parentRadius * 0.333, printedGenerations + 1)

    return

if __name__=='__main__':
    printedGenerations = 0
    # Closes any still open plots
    plt.close()

    argument = ArgumentParser()
    argument.add_argument('-file', metavar='FILE', help='input file for Circles. <Ratio> <R> <G> <B>')
    args = argument.parse_args()
    lines = []

    if(args.file):
        with open(args.file) as file:
            for line in open(args.file):
                line = shlex.split(line)
                lines.append(line)
    # 
    if len(lines) > 0:
        generations = len(lines)

    i = 0
    ratio = []
    for i in lines: 
        ratio.append( float( Fraction(i[0])))
        colorList.append([int(i[1])/255, int(i[2])/255, int(i[3])/255])
        
    if(not args.file):
        create_colors()

    if(generations > 1):
        if len(lines) > 0:
            create_parent(300 * ratio[printedGenerations])
        else:
            create_parent(300)
        printedGenerations += 1
    if(generations >= 2):
        if len(lines) > 0:
            create_cluster(0, 0, 300 * ratio[printedGenerations], printedGenerations)
        else:
            create_cluster(0, 0, 300 * 0.333, printedGenerations)

    graph_size = 300
    fig, ax = plt.subplots()
    c = 0
    for c in circles:
        ax.add_artist(c)
  
    plt.axis('scaled')
    plt.axis([-graph_size, graph_size, -graph_size, graph_size])
    plt.axis('off')

    plt.show()

