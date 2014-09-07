require_relative 'QuickUnionUF'

describe QuickUnionUF do
  let(:quuf) { QuickUnionUF.new(10) }

  it 'should connect components' do

    quuf.union(4,3)
    quuf.union(3,8)
    quuf.union(6,5)
    quuf.union(9,4)
    quuf.union(2,1)

    quuf.connected(8,9).should eq (true)
    quuf.connected(5,4).should eq (false)

    quuf.union(5,0)
    quuf.union(7,2)
    quuf.union(6,1)
    quuf.union(7,3)

    quuf.id.should eq [1, 8, 1, 8, 3, 0, 5, 1, 8, 8]

  end

end
